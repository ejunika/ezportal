package com.ez.jdbc.factory;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlRootElement(name = "table")
@JsonRootName(value = "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseTable implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tableName;
    
    private List<TableColumn> tableColumns;

    public List<TableColumn> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(List<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
