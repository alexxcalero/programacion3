using RHStoreBaseBO.ServiciosWeb;
using System;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;

namespace RHStoreComprasBO
{
    public class PayPalAPI
    {
        private string clientId;
        private string clientSecret;
        public PayPalAPI()
        {
            this.clientId = "AT3qKPWFLqjokKJcgBtV7T54TyFzYNtG8HUiC6-ba2Q-r0F2Iyc8LIlaRqjv-9yfOZAckq_WCnfE9A3z";            
            this.clientSecret = "EKrBCfI3mpICyV9cC3Gu7vaNVfBOSspPaJhfcDMJ-W9TC1HaHrCsw48touO0cHVPOc1IzXhe5G4ffsG5";
        }

        public string get_token()
        {
            string token = "-";
            string url = "https://api-m.sandbox.paypal.com/v1/oauth2/token";
            string grantType = "grant_type=client_credentials";
            string credentials = $"{this.clientId}:{this.clientSecret}";
            string encodedCredentials = Convert.ToBase64String(Encoding.UTF8.GetBytes(credentials));

            try
            {
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
                request.Method = "POST";
                request.ContentType = "application/x-www-form-urlencoded";
                request.Headers["Authorization"] = $"Basic {encodedCredentials}";

                byte[] bodyData = Encoding.UTF8.GetBytes(grantType);
                request.ContentLength = bodyData.Length;
                using (Stream requestStream = request.GetRequestStream())
                {
                    requestStream.Write(bodyData, 0, bodyData.Length);
                }

                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    using (StreamReader reader = new StreamReader(response.GetResponseStream()))
                    {
                        string responseText = reader.ReadToEnd();
                        token = this.extract_access_token(responseText);
                    }
                }
            }
            catch (WebException ex)
            {
                using (var errorResponse = (HttpWebResponse)ex.Response)
                {
                    using (var reader = new StreamReader(errorResponse.GetResponseStream()))
                    {
                        string errorText = reader.ReadToEnd();
                        Console.WriteLine("Error:");
                        Console.WriteLine(errorText);
                    }
                }
            }

            return token;
        }

        private string extract_access_token(string json)
        {
            string tokenKey = "\"access_token\":\"";
            int tokenStartIndex = json.IndexOf(tokenKey) + tokenKey.Length;
            if (tokenStartIndex < tokenKey.Length)
            {
                return null;
            }

            int tokenEndIndex = json.IndexOf("\"", tokenStartIndex);
            if (tokenEndIndex == -1) 
            {
                return null;
            }

            return json.Substring(tokenStartIndex, tokenEndIndex - tokenStartIndex);
        }

        public string create_order(int requestId, int referenceId,
            string currencyCode, double value, string returnUrl, string cancelUrl,
            string brandName)
        {
            string link = "-";
            string url = "https://api-m.sandbox.paypal.com/v2/checkout/orders";
            string jsonBody = $@"
            {{
                ""intent"": ""CAPTURE"",
                ""purchase_units"": [
                    {{
                        ""reference_id"": ""{referenceId.ToString()}"",
                        ""amount"": {{
                            ""currency_code"": ""{currencyCode}"",
                            ""value"": ""{value.ToString()}""
                        }}
                    }}
                ],
                ""payment_source"": {{
                    ""paypal"": {{
                        ""experience_context"": {{
                            ""payment_method_preference"": ""IMMEDIATE_PAYMENT_REQUIRED"",
                            ""brand_name"": ""{brandName}"",
                            ""locale"": ""en-US"",
                            ""landing_page"": ""LOGIN"",
                            ""shipping_preference"": ""NO_SHIPPING"",
                            ""user_action"": ""PAY_NOW"",
                            ""return_url"": ""{returnUrl}"",
                            ""cancel_url"": ""{cancelUrl}""
                        }}
                    }}
                }}
            }}";

            try
            {
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
                request.Method = "POST";
                request.ContentType = "application/json";
                request.Headers["PayPal-Request-Id"] = requestId.ToString();
                request.Headers["Authorization"] = "Bearer " + this.get_token();

                byte[] bodyData = Encoding.UTF8.GetBytes(jsonBody);
                request.ContentLength = bodyData.Length;

                using (Stream requestStream = request.GetRequestStream())
                {
                    requestStream.Write(bodyData, 0, bodyData.Length);
                }

                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    using (StreamReader reader = new StreamReader(response.GetResponseStream()))
                    {
                        string responseText = reader.ReadToEnd();
                        link = this.extract_link(responseText);
                    }
                }
            }
            catch (WebException ex)
            {
                using (var errorResponse = (HttpWebResponse)ex.Response)
                {
                    using (var reader = new StreamReader(errorResponse.GetResponseStream()))
                    {
                        string errorText = reader.ReadToEnd();
                        Console.WriteLine("Error:");
                        Console.WriteLine(errorText);
                    }
                }
            }

            return link;
        }

        public string extract_link(string jsonResponse)
        {
            int index_start = jsonResponse.IndexOf("https://www.sandbox.paypal.com");
            string url_dirty = jsonResponse.Substring(index_start, 90);
            int i;
            for ( i = 0; i < url_dirty.Length; i++)
            {
                if (url_dirty[i] == '"')
                {
                    break;
                }
            }
            
            return url_dirty.Substring(0, i);
        }

        public bool capture_order(string order_id)
        {
            bool completed = false;
            //string order_id = this.extract_order_id_from_link(link);
            string responseText = string.Empty;
            string url = $"https://api-m.sandbox.paypal.com/v2/checkout/orders/{order_id}/capture";

            string requestBody = "{}";

            try
            {
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
                request.Method = "POST";
                request.ContentType = "application/json";
                request.Headers["Authorization"] = "Bearer " + this.get_token();

                request.Headers["PayPal-Request-Id"] = order_id;

                // Write the request body if necessary
                byte[] bodyData = Encoding.UTF8.GetBytes(requestBody);
                request.ContentLength = bodyData.Length;
                using (Stream requestStream = request.GetRequestStream())
                {
                    requestStream.Write(bodyData, 0, bodyData.Length);
                }

                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    using (StreamReader reader = new StreamReader(response.GetResponseStream()))
                    {
                        responseText = reader.ReadToEnd();
                        completed = ("COMPLETED" == this.extract_status(responseText));
                    }
                }
            }
            catch (WebException ex)
            {
                using (var errorResponse = (HttpWebResponse)ex.Response)
                {
                    using (var reader = new StreamReader(errorResponse.GetResponseStream()))
                    {
                        string errorText = reader.ReadToEnd();
                        Console.WriteLine("Error:");
                        Console.WriteLine(errorText);
                    }
                }
            }

            return completed;
        }
        public string extract_order_id_from_link(string url)
        {
            string token = string.Empty;
            try
            {
                int tokenIndex = url.IndexOf("token=");

                if (tokenIndex != -1)
                {
                    tokenIndex += 6;
                    int endIndex = url.IndexOf("&", tokenIndex);

                    if (endIndex == -1)  
                    {
                        endIndex = url.Length;
                    }

                    token = url.Substring(tokenIndex, endIndex - tokenIndex);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error extracting token: {ex.Message}");
            }

            return token;
        }

        public string extract_status(string jsonResponse)
        {
            string statusKey = "\"status\":\"";
            int statusIndex = jsonResponse.IndexOf(statusKey);

            if (statusIndex != -1)
            {
                int startIndex = statusIndex + statusKey.Length;
                int endIndex = jsonResponse.IndexOf("\"", startIndex);

                if (endIndex != -1)
                {
                    return jsonResponse.Substring(startIndex, endIndex - startIndex);
                }
            }

            return string.Empty;
        }
    }
}
