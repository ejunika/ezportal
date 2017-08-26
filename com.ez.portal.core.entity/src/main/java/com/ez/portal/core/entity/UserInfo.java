package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER_INFO")
@XmlRootElement(name = "userInfo")
public class UserInfo extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UserInfoIdGenerator")
    @GenericGenerator(name = "UserInfoIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "USER_INFO_ID")
    private Long userInfoId;
    
    @Column(name = "USER_INFO_NAME")
    private String userInfoName;
    
    @Column(name = "USER_INFO_VALUE")
    private String userInfoValue;
    
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserInfoName() {
        return userInfoName;
    }

    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }

    public String getUserInfoValue() {
        return userInfoValue;
    }

    public void setUserInfoValue(String userInfoValue) {
        this.userInfoValue = userInfoValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
