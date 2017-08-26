package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ACCESS_PERMISSION")
@XmlRootElement(name = "accessPermission")
public class AccessPermission extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "AccessPermissionIdGenerator")
    @GenericGenerator(name = "AccessPermissionIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "ACCESS_PERMISSION_ID")
    private Long accessPermissionId;
    
    @Column(name = "ACCESS_PERMISSION_NAME")
    private String accessPermissionName;

    public Long getAccessPermissionId() {
        return accessPermissionId;
    }

    public void setAccessPermissionId(Long accessPermissionId) {
        this.accessPermissionId = accessPermissionId;
    }

    public String getAccessPermissionName() {
        return accessPermissionName;
    }

    public void setAccessPermissionName(String accessPermissionName) {
        this.accessPermissionName = accessPermissionName;
    }
    
}
