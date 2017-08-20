package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "USER")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends AbstractEntity {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @GeneratedValue(generator = "UserIdGenerator")
    @GenericGenerator(name = "UserIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * 
     */
    @Column(name = "EMAIL_ID", unique = true)
    private String emailId;

    /**
     * 
     */
    @Column(name = "USERNAME", unique = true)
    private String username;
    
    /**
     * 
     */
    @Column(name = "USER_TYPE")
    private Byte userType;
    
    /**
     * 
     */
    public User() {
        super();
    }
    
    /**
     * @param emailId
     * @param username
     * @param userType
     */
    public User(String emailId, String username, Byte userType) {
        super();
        this.emailId = emailId;
        this.username = username;
        this.userType = userType;
    }
    
    /**
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

}
