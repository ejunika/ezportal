package com.ez.portal.core.entity;

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

/**
 * @author azaz.akhtar
 *
 */
@Entity
@Table(name = "HIBERNATE_PROPERTY")
@XmlRootElement(name = "hibernateProperty")
@XmlAccessorType(XmlAccessType.FIELD)
public class HibernateProperty extends AbstractEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @GeneratedValue(generator = "HibernatePropertyIdGenerator")
    @GenericGenerator(name = "HibernatePropertyIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "PROPERTY_ID")
    private Long propertyId;
    
    /**
     * 
     */
    @Column(name = "PROPERTY_NAME")
    private String propertyName;
    
    /**
     * 
     */
    @Column(name = "PROPERTY_VALUE")
    private String propertyValue;
    
    /**
     * 
     */
    @ManyToOne
    @JoinColumn(name = "USER_SPACE")
    private UserSpace userSpace;
        
    /**
     * 
     */
    public HibernateProperty() {
        super();
    }
    
    /**
     * @param propertyName
     * @param propertyValue
     */
    public HibernateProperty(String propertyName, String propertyValue) {
        super();
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }
    
    /**
     * @param propertyName
     * @param propertyValue
     */
    public HibernateProperty(String propertyName, String propertyValue, Byte entryStatus) {
    	super(entryStatus);
    	this.propertyName = propertyName;
    	this.propertyValue = propertyValue;
    }
    
    /**
     * @return
     */
    public Long getPropertyId() {
        return propertyId;
    }
    
    /**
     * @param propertyId
     */
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
    
    /**
     * @return
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * @param propertyName
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * @return
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    /**
     * @return
     */
    public UserSpace getUserSpace() {
        return userSpace;
    }

    /**
     * @param userSpace
     */
    public void setUserSpace(UserSpace userSpace) {
        this.userSpace = userSpace;
    }
    
}
