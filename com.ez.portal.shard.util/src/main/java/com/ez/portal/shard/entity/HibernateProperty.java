package com.ez.portal.shard.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "HIBERNATE_PROPERTY")
@XmlRootElement(name = "hibernateProperty")
@XmlAccessorType(XmlAccessType.FIELD)
public class HibernateProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "HibernatePropertyIdGenerator")
    @GenericGenerator(name = "HibernatePropertyIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "PROPERTY_ID")
    private Long propertyId;
    
    @Column(name = "PROPERTY_NAME")
    private String propertyName;
    
    @Column(name = "PROPERTY_VALUE")
    private String propertyValue;
    
    @ManyToOne
    @JoinColumn(name = "USER_SPACE")
    private UserSpace userSpace;
    
    @Column(name = "CREATED_AT")
    private Date createdAt;
    
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
    
    public Long getPropertyId() {
        return propertyId;
    }
    
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    public String getPropertyValue() {
        return propertyValue;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserSpace getUserSpace() {
        return userSpace;
    }

    public void setUserSpace(UserSpace userSpace) {
        this.userSpace = userSpace;
    }
    
}
