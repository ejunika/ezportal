package com.ez.portal.di.entity;

import java.util.Date;

import com.ez.portal.core.entity.User;

public class Job {
    
    private Long jobId;
    
    private Byte jobType;
    
    private Byte jobStatus;
    
    private Byte jobPriority;
    
    private Date jobCreationDate;
    
    private Date jobUpdationDate;
    
    private User createdBy;
    
    private User updatedBy;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Byte getJobType() {
        return jobType;
    }

    public void setJobType(Byte jobType) {
        this.jobType = jobType;
    }

    public Byte getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Byte jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Byte getJobPriority() {
        return jobPriority;
    }

    public void setJobPriority(Byte jobPriority) {
        this.jobPriority = jobPriority;
    }

    public Date getJobCreationDate() {
        return jobCreationDate;
    }

    public void setJobCreationDate(Date jobCreationDate) {
        this.jobCreationDate = jobCreationDate;
    }

    public Date getJobUpdationDate() {
        return jobUpdationDate;
    }

    public void setJobUpdationDate(Date jobUpdationDate) {
        this.jobUpdationDate = jobUpdationDate;
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
    
}
