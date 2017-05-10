package com.ez.mapping;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "columnMapping")
@XmlAccessorType(XmlAccessType.FIELD)
public class MappingJson {
    
    private Long importId;
    
    private String srcFileName;
    
    private String destObject;
    
    private List<ColumnMapping> columnMappings;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    public String getSrcFileName() {
        return srcFileName;
    }

    public void setSrcFileName(String srcFileName) {
        this.srcFileName = srcFileName;
    }

    public String getDestObject() {
        return destObject;
    }

    public void setDestObject(String destObject) {
        this.destObject = destObject;
    }

    public List<ColumnMapping> getColumnMappings() {
        return columnMappings;
    }

    public void setColumnMappings(List<ColumnMapping> columnMappings) {
        this.columnMappings = columnMappings;
    }
    
}
