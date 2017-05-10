package com.ez.jdbc.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JdbcMetaDataFactory {

    public String getMetaDataAsJson(Connection connection) {
        List<DatabaseTable> databaseTables = new ArrayList<>();
        DatabaseMetaData databaseMetaData = null;
        ResultSet tablesAsResultSet = null;
        String json = null;
        try {
            databaseMetaData = connection.getMetaData();
            tablesAsResultSet = databaseMetaData.getTables(null, null, "%", null);
            ResultSet columnsAsResultSet = null;
            while (tablesAsResultSet.next()) {
                DatabaseTable databaseTable = new DatabaseTable();
                String tableName = tablesAsResultSet.getString(3);
                databaseTable.setTableName(tableName);
                columnsAsResultSet = databaseMetaData.getColumns(null, null, tableName, null);
                List<TableColumn> tableColumns = new ArrayList<>();
                while (columnsAsResultSet.next()) {
                    TableColumn tableColumn = new TableColumn();
                    String columnName = columnsAsResultSet.getString("COLUMN_NAME");
                    String typeName = columnsAsResultSet.getString("TYPE_NAME");
                    String remarks = columnsAsResultSet.getString("REMARKS");
                    tableColumn.setColumnName(columnName);
                    tableColumn.setTypeName(typeName);
                    tableColumn.setRemarks(remarks);
                    tableColumns.add(tableColumn);
                    databaseTable.setTableColumns(tableColumns);
                }
                databaseTables.add(databaseTable);
            }
            json = new ObjectMapper().writeValueAsString(databaseTables);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    public void writeAsFile(String data, String fileName) {
        
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/PORTAL", "root", "Admin");
        new JdbcMetaDataFactory().getMetaDataAsJson(c);
    }
}
