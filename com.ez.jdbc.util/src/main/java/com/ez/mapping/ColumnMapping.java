package com.ez.mapping;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "columnMapping")
@XmlAccessorType(XmlAccessType.FIELD)
public class ColumnMapping implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String srcCol;
    
    private String destCol;
    
    private String destType;
    
    public String getSrcCol() {
        return srcCol;
    }
    
    public void setSrcCol(String srcCol) {
        this.srcCol = srcCol;
    }
    
    public String getDestCol() {
        return destCol;
    }
    
    public void setDestCol(String destCol) {
        this.destCol = destCol;
    }
    
    public String getDestType() {
        return destType;
    }
    
    public void setDestType(String destType) {
        this.destType = destType;
    }
    
}
