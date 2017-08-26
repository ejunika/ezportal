package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LOGIN")
@XmlRootElement(name = "login")
public class Login extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "LoginIdGenerator")
    @GenericGenerator(name = "LoginIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "LOGIN_ID")
    private Long loginId;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

}
