package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER_ROLE")
@XmlRootElement(name = "userRole")
public class UserRole extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UserRoleIdGenerator")
    @GenericGenerator(name = "UserRoleIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "USER_ROLE_ID")
    private Long userRoleId;
    
    @Column(name = "USER_ROLE_NAME")
    private String userRoleName;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
    
}
