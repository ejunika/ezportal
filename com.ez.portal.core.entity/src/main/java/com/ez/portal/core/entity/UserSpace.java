package com.ez.portal.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER_SPACE")
@XmlRootElement(name = "userSpace")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSpace extends AbstractEntity {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @GeneratedValue(generator = "UserSpaceIdGenerator")
    @GenericGenerator(name = "UserSpaceIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "USER_SPACE_ID")
    private Long userSpaceId;
    
    /**
     * 
     */
    @Column(name = "USER_SPACE_NAME")
    private String userSpaceName;
    
    /**
     * 
     */
    @Column(name = "USER_SPACE_DISPLAY_NAME")
    private String userSpaceDisplayName;
    
    /**
     * @return userSpaceDisplayName
     */
    public String getUserSpaceDisplayName() {
		return userSpaceDisplayName;
	}

	/**
	 * @param userSpaceDisplayName
	 */
	public void setUserSpaceDisplayName(String userSpaceDisplayName) {
		this.userSpaceDisplayName = userSpaceDisplayName;
	}

	/**
     * 
     */
    @Column(name = "USER_SPACE_STATUS")
    private Byte userSpaceStatus;
    
    /**
     * 
     */
    @OneToMany(mappedBy = "userSpace")
    private List<HibernateProperty> hibernateProperties;
    
    /**
     * 
     */
    public UserSpace() {
        super();
    }
    
    /**
     * @param userSpaceName
     */
    public UserSpace(String userSpaceName) {
        super();
        this.userSpaceName = userSpaceName;
    }
    
    /**
     * @param userSpaceName
     * @param userSpaceDisplayName
     */
    public UserSpace(String userSpaceName, String userSpaceDisplayName) {
    	super();
    	this.userSpaceName = userSpaceName;
    	this.userSpaceDisplayName = userSpaceDisplayName;
    }

    /**
     * @return
     */
    public Long getUserSpaceId() {
        return userSpaceId;
    }

    /**
     * @param userSpaceId
     */
    public void setUserSpaceId(Long userSpaceId) {
        this.userSpaceId = userSpaceId;
    }

    /**
     * @return
     */
    public String getUserSpaceName() {
        return userSpaceName;
    }

    /**
     * @param userSpaceName
     */
    public void setUserSpaceName(String userSpaceName) {
        this.userSpaceName = userSpaceName;
    }

    /**
     * @return
     */
    public List<HibernateProperty> getHibernateProperties() {
        return hibernateProperties;
    }

    /**
     * @param hibernateProperties
     */
    public void setHibernateProperties(List<HibernateProperty> hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    /**
     * @return
     */
    public Byte getUserSpaceStatus() {
        return userSpaceStatus;
    }

    /**
     * @param userSpaceStatus
     */
    public void setUserSpaceStatus(Byte userSpaceStatus) {
        this.userSpaceStatus = userSpaceStatus;
    }

}
