package com.ez.portal.core.entity;

import java.util.Date;

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
public class User implements Shardable {
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

    @Column(name = "CREATED_AT")
    private Date createdAt;
    
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "SHARD_KEY")
    private String shardKey;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getShardKey() {
        return shardKey;
    }

    public void setShardKey(String shardKey) {
        this.shardKey = shardKey;
    }

}
