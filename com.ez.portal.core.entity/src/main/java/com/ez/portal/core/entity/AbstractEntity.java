package com.ez.portal.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractEntity implements Shardable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_AT")
    private Date createdAt;
    
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;
    
    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;
    
    @Column(name = "SHARD_KEY")
    private String shardKey;
    
    @Column(name = "ENTRY_STATUS")
    private Byte entryStatus;
    
    public AbstractEntity() {
        super();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    
    public AbstractEntity(Byte entryStatus) {
    	super();
    	this.createdAt = new Date();
    	this.updatedAt = new Date();
    	this.entryStatus = entryStatus;
    }
    
    public AbstractEntity(User createdBy, User updatedBy) {
        super();
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.shardKey = createdBy.getShardKey();
    }
    
    public AbstractEntity(User createdBy, User updatedBy, Byte entryStatus) {
    	super();
    	this.createdAt = new Date();
    	this.updatedAt = new Date();
    	this.createdBy = createdBy;
    	this.updatedBy = updatedBy;
    	this.entryStatus = entryStatus;
    	this.shardKey = createdBy.getShardKey();
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String getShardKey() {
        return shardKey;
    }
    
    public void setShardKey(String shardKey) {
        this.shardKey = shardKey;
    }

	public Byte getEntryStatus() {
		return entryStatus;
	}

	public void setEntryStatus(Byte entryStatus) {
		this.entryStatus = entryStatus;
	}
    
}
