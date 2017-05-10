package com.ez.mapping;

import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class QueryBuilder {
    
    public static void main(String[] args) {
        ColumnMapping mapping1 = new ColumnMapping();
        mapping1.setSrcCol("School_id");
        mapping1.setDestCol("SCHOOL_ID");
        mapping1.setDestType("varchar");
        
        ColumnMapping mapping2 = new ColumnMapping();
        mapping2.setSrcCol("School_name");
        mapping2.setDestCol("SCHOOL_NAME");
        mapping2.setDestType("varchar");
        
        MappingJson mappingJson = new MappingJson();
        mappingJson.setColumnMappings(new ArrayList<ColumnMapping>());
        mappingJson.getColumnMappings().add(mapping1);
        mappingJson.getColumnMappings().add(mapping2);
        
        new QueryBuilder().createQuery(mappingJson, null);
    }
    public String createQuery(MappingJson mappingJson, CSVReader reader) {
        StringBuilder query = new StringBuilder();
        String tableName = mappingJson.getDestObject();
        List<ColumnMapping> columnMappings = mappingJson.getColumnMappings();
        query.append("INSERT INTO " + tableName + " (");
        for (ColumnMapping columnMapping : columnMappings) {
            query.append("`" + columnMapping.getDestCol() + "`, ");
        }
        return query.toString();
    }
}
