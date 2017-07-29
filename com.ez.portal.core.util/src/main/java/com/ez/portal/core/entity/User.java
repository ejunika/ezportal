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

@Entity
@Table(name = "USER")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UserIdGenerator")
    @GenericGenerator(name = "UserIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "EMAIL_ID", unique = true)
    private String emailId;

    @Column(name = "USERNAME", unique = true)
    private String username;
    
    public User() {
        super();
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
