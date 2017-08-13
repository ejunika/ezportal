package com.ez.portal.shard.entity;

import java.io.Serializable;
import java.util.Date;
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
public class UserSpace implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UserSpaceIdGenerator")
    @GenericGenerator(name = "UserSpaceIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "USER_SPACE_ID")
    private Long userSpaceId;
    
    @Column(name = "USER_SPACE_NAME")
    private String userSpaceName;
    
    @Column(name = "USER_SPACE_STATUS")
    private Byte userSpaceStatus;
    
    @Column(name = "CREATED_AT")
    private Date createdAt;
    
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
    
    @OneToMany(mappedBy = "userSpace")
    private List<HibernateProperty> hibernateProperties;
    
    public UserSpace() {
        super();
    }
    
    public UserSpace(String userSpaceName) {
        super();
        this.userSpaceName = userSpaceName;
    }

    public Long getUserSpaceId() {
        return userSpaceId;
    }

    public void setUserSpaceId(Long userSpaceId) {
        this.userSpaceId = userSpaceId;
    }

    public String getUserSpaceName() {
        return userSpaceName;
    }

    public void setUserSpaceName(String userSpaceName) {
        this.userSpaceName = userSpaceName;
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

    public List<HibernateProperty> getHibernateProperties() {
        return hibernateProperties;
    }

    public void setHibernateProperties(List<HibernateProperty> hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    public Byte getUserSpaceStatus() {
        return userSpaceStatus;
    }

    public void setUserSpaceStatus(Byte userSpaceStatus) {
        this.userSpaceStatus = userSpaceStatus;
    }

}
