package com.lucky.qa.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelper {
    static Connection connection = null;

    public static String databaseName(String portalLanguage) {
        String DatabaseName = null;
        if (portalLanguage.equals("French") || portalLanguage.equals("Arabic_Morocco")) {
            return DatabaseName = "LuckyMorocco";
        } else if (portalLanguage.equals("English") || portalLanguage.equals("Arabic_Egypt")) {
            return DatabaseName = "LuckyStaging";
        }
        return DatabaseName;
    }

    public static void setUpDBConnection(String portalLanguage) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://luckydbsrv.database.windows.net:1433;database=" +
                    databaseName(portalLanguage) + ";", "Azure_WriteLogin", "N3wLuc$$@2");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getValueFromDatabase(String query) {
        String res = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next())
                res = result.getString(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public static String updateDatabaseValues(String query) {
        String res = null;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public static String executeQuery(String query) {
        String res = null;
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public static void closeDBConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
