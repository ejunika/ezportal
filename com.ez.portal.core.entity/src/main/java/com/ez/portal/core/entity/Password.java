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

@Entity
@Table(name = "PASSWORD")
@XmlRootElement(name = "password")
@XmlAccessorType(XmlAccessType.FIELD)
public class Password extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "PasswordIdGenerator")
    @GenericGenerator(name = "PasswordIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "PASSWORD_ID")
    private Long passwordId;
    
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;
        
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    public Password() {
        super();
    }
    
    public Password(User user, String passwordHash, Byte entryStatus) {
        super(user, user, entryStatus);
        this.passwordHash = passwordHash;
    }

    public Long getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Long passwordId) {
        this.passwordId = passwordId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
