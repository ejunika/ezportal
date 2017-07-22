package com.ez.jdbc.util.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.ez.portal.shard.entity.UserSpace;

public class EZMain {

    public void testOperation() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PORTAL", "root", "Admin");
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet tables = metaData.getTables(null, null, "%", null);
                while (tables.next()) {
                    String tableName = tables.getString(3);
                    System.out.println(tableName);
                    ResultSet columns = metaData.getColumns(null, null, tableName, null);
                    ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
                    while (primaryKeys.next()) {
                        String primaryKey = primaryKeys.getString(4);
                        System.out.println(primaryKey);
                    }
                    while (columns.next()) {
                        String columnName = columns.getString("COLUMN_NAME");
                        String typeName = columns.getString("TYPE_NAME");
                        String remarks = columns.getString("REMARKS");
                        System.out.println(
                                "\tCOLUMN_NAME: " + columnName + ", TYPE_NAME: " + typeName + ", REMARKS: " + remarks);
                    }
                }
                System.out.println("Done");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void checkWebClient() {
        WebClient client = WebClient.create("http://localhost:8082/com.ez.portal/rest/install");
        client.path("create_user_space");
        client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
//        UserSpaceRequest r = new UserSpaceRequest();
        UserSpace us = new UserSpace();
        us.setUserSpaceName("Abc");
//        r.setUserSpace(us);
        try {
//            String str = new ObjectMapper().writeValueAsString(r);
//            String x = client.post(str, String.class);
//            UserSpaceResponse urs = new ObjectMapper().readValue(x, UserSpaceResponse.class);
//            System.out.println(urs.getUserSpaces().get(0).getUserSpaceId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new EZMain().testOperation();
        new EZMain().checkWebClient();;
    }

}
