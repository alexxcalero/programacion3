using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftDBManager
{
    public class DBManager
    {
        private static DBManager dbManager = null;
        private MySqlConnection con;
        private static string url = "server=labs-1inf30-prog3-20242.c8ttwc6gkvxy.us-east-1.rds.amazonaws.com;";
        private static string username = "user=admin;";
        private static string password = "password=prog320242labs;";
        private static string bd = "database=lab06;";
        private static string port = "port=3306;";

        private DBManager() { }
        public static DBManager Instance
        {
            get
            {
                if (dbManager == null)
                    createInstance();
                return dbManager;
            }
        }

        private static void createInstance()
        {
            if (dbManager == null)
                dbManager = new DBManager();
        }

        public MySqlConnection Connection
        {
            get
            {
                string cadena = url + username + password + port + bd;
                con = new MySqlConnection(cadena);
                return con;
            }
        }
    }
}
