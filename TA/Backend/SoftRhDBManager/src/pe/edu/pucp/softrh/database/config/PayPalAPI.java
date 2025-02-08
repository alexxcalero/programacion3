package pe.edu.pucp.softrh.database.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;

public class PayPalAPI {

    private String url_base;
    private String id_cliente;
    private String secreto;
    private String autorizacion;
    private String autorizacion_cifrada;
    private String token_acceso;
    private String orderID;
    private HttpClient cliente;

    private String url_return;
    private String order_id;

    public PayPalAPI( ) {
        this.url_base = "https://api-m.sandbox.paypal.com/";
        this.cliente = HttpClient.newHttpClient();
        this.set_idcliente_secreto("AT3qKPWFLqjokKJcgBtV7T54TyFzYNtG8HUiC6-ba2Q-r0F2Iyc8LIlaRqjv-9yfOZAckq_WCnfE9A3z",
                "EKrBCfI3mpICyV9cC3Gu7vaNVfBOSspPaJhfcDMJ-W9TC1HaHrCsw48touO0cHVPOc1IzXhe5G4ffsG5");
    }

    public void set_idcliente_secreto(String clientId, String secret) {
        this.id_cliente = clientId;
        this.secreto = secret;
        this.autorizacion = this.id_cliente + ":" + this.secreto;
        this.autorizacion_cifrada = Base64.getEncoder().encodeToString(autorizacion.getBytes());
    }

    private ArrayList<String>[] extraer_datos_json(String respuesta) {
        respuesta = respuesta.trim().substring(1, respuesta.length() - 1);
        String[] pairs = respuesta.split(",");

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);

            String key = keyValue[0].trim().replace("\"", "");
            String value = keyValue[1].trim().replace("\"", "");

            keys.add(key);
            values.add(value);
        }

        return new ArrayList[]{keys, values};
    }

    public ArrayList<String>[] obtener_token_acceso(String url_autorizacion) throws IOException, InterruptedException {
        HttpRequest requerimiento = HttpRequest.newBuilder()
                .uri(URI.create(this.url_base + url_autorizacion))
                .header("Authorization", "Basic " + this.autorizacion_cifrada)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();
        HttpResponse<String> response = this.cliente.send(requerimiento, HttpResponse.BodyHandlers.ofString());

        //System.out.println("Response code: " + response.statusCode());
        ArrayList<String>[] result = this.extraer_datos_json(response.body());
        ArrayList<String> values = result[1];
        this.token_acceso = values.get(1);

        return result;
    }

    public String crear_datos_pago(String senderBatchId,
            String emailSubject, String emailMessage,
            String amountValue, String currency,
            String note, String senderItemId, String receiver) {
        String recipientType = "EMAIL";
        StringBuilder payoutJson = new StringBuilder();

        payoutJson.append("{")
                .append("\"sender_batch_header\": {")
                .append("\"sender_batch_id\": \"").append(senderBatchId).append("\",")
                .append("\"email_subject\": \"").append(emailSubject).append("\",")
                .append("\"email_message\": \"").append(emailMessage).append("\"")
                .append("},")
                .append("\"items\": [")
                .append("{")
                .append("\"recipient_type\": \"").append(recipientType).append("\",")
                .append("\"amount\": {")
                .append("\"value\": \"").append(amountValue).append("\",")
                .append("\"currency\": \"").append(currency).append("\"")
                .append("},")
                .append("\"note\": \"").append(note).append("\",")
                .append("\"sender_item_id\": \"").append(senderItemId).append("\",")
                .append("\"receiver\": \"").append(receiver).append("\"")
                .append("}")
                .append("]}");

        return payoutJson.toString();
    }

    public ArrayList<String>[] realizar_pago(String senderBatchId,
            String emailSubject, String emailMessage,
            String amountValue, String currency,
            String note, String senderItemId, String receiver) throws IOException, InterruptedException {
        String datos_pago = this.crear_datos_pago(senderBatchId,
                emailSubject, emailMessage,
                amountValue, currency,
                note, senderItemId, receiver);
        ArrayList<String>[] respuesta_token = this.obtener_token_acceso("v1/oauth2/token");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-m.sandbox.paypal.com/v1/payments/payouts"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token_acceso)
                .POST(HttpRequest.BodyPublishers.ofString(datos_pago))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println("Response code: " + response.statusCode());
        ArrayList<String>[] result = this.extraer_datos_json(response.body());
        ArrayList<String> values = result[1];

        return result;
    }

    public String createOrderJson(String referenceId, String currencyCode, String value,
            String returnUrl, String cancelUrl, String brandName) {

        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("{")
                .append("\"intent\": \"CAPTURE\",")
                .append("\"purchase_units\": [")
                .append("{")
                .append("\"reference_id\": \"").append(referenceId).append("\",")
                .append("\"amount\": {")
                .append("\"currency_code\": \"").append(currencyCode).append("\",")
                .append("\"value\": \"").append(value).append("\"")
                .append("}")
                .append("}")
                .append("],")
                .append("\"payment_source\": {")
                .append("\"paypal\": {")
                .append("\"experience_context\": {")
                .append("\"payment_method_preference\": \"IMMEDIATE_PAYMENT_REQUIRED\",")
                .append("\"brand_name\": \"").append(brandName).append("\",")
                .append("\"locale\": \"en-US\",")
                .append("\"landing_page\": \"LOGIN\",")
                .append("\"shipping_preference\": \"NO_SHIPPING\",")
                .append("\"user_action\": \"PAY_NOW\",")
                .append("\"return_url\": \"").append(returnUrl).append("\",")
                .append("\"cancel_url\": \"").append(cancelUrl).append("\"")
                .append("}")
                .append("}")
                .append("}")
                .append("}");

        return jsonBuilder.toString();
    }

    public String sendOrderRequest(String requestId, String referenceId,
            String currencyCode, String value,
            String returnUrl, String cancelUrl, String brandName) throws IOException, InterruptedException {
        String datos = this.createOrderJson(referenceId, currencyCode, value,
                returnUrl, cancelUrl, brandName);
        ArrayList<String>[] respuesta_token = this.obtener_token_acceso("v1/oauth2/token");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-m.sandbox.paypal.com/v2/checkout/orders"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token_acceso)
                .header("PayPal-Request-Id", requestId)
                .POST(HttpRequest.BodyPublishers.ofString(datos))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println("Response code: " + response.statusCode());

        ArrayList<String>[] result = this.extraer_datos_json(response.body());
        ArrayList<String> keys = result[0];
        ArrayList<String> values = result[1];
        this.orderID = values.get(0);

        return values.get(6);
    }

    private String extractStatusFromJson(String json) {

        String statusKey = "\"status\":\"";
        int statusKeyPos = json.indexOf(statusKey);
        if (statusKeyPos != -1) {
            int statusStart = statusKeyPos + statusKey.length();
            int statusEnd = json.indexOf("\"", statusStart);
            if (statusEnd != -1) {
                return json.substring(statusStart, statusEnd);
            }
        }
        return "";
    }

    public Boolean verificate_pay(String url_return) throws URISyntaxException, IOException, InterruptedException {
        String tokenKey = "token=";
        int tokenStartIndex = url_return.indexOf(tokenKey) + tokenKey.length();
        String order_id = url_return.substring(tokenStartIndex);
        ArrayList<String>[] respuesta_token = this.obtener_token_acceso("v1/oauth2/token");
        String url = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + order_id + "/capture";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token_acceso)
                .header("PayPal-Request-Id", order_id)
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        String status = this.extractStatusFromJson(json);
        return "COMPLETED".equals(status);
    }
}
