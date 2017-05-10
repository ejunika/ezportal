package com.ez.portal.core.entity;

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
@Table(name = "LOGIN")
@XmlRootElement(name = "login")
@XmlAccessorType(XmlAccessType.FIELD)
public class Login implements Serializable, Shardable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "LoginIdGenerator")
    @GenericGenerator(name = "LoginIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "LOGIN_ID")
    private Long loginId;

    @ManyToOne
    @JoinColumn(name = "LOGIN_BY")
    private User user;
    
    @Column(name = "SHARD_KEY")
    private String shardKey;
    
    @Column(name = "CREATED_AT")
    private Date createdAt;
    
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getShardKey() {
        return shardKey;
    }

    public void setShardKey(String shardKey) {
        this.shardKey = shardKey;
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

}
