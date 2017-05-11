package com.ez.portal.core.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "portalRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PortalRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}
