package com.ez.portal.di.entity;

import java.util.Date;

import com.ez.portal.core.entity.User;

public class File {
    
    private Long fileId;
    
    private String systemFileName;
    
    private String userFileName;
    
    private String systemFileLocation;
    
    private Byte fileType;
    
    private Byte objectType;
    
    private Byte fileStatus;
    
    private Date processBeginDate;
    
    private Date processEndDate;
    
    private Job importJob;
    
    private Date fileCreationDate;
    
    private Date fileUpdationDate;
    
    private User createdBy;
    
    private User updatedBy;
    
    public Long getFileId() {
        return fileId;
    }
    
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
    
    public String getSystemFileName() {
        return systemFileName;
    }
    
    public void setSystemFileName(String systemFileName) {
        this.systemFileName = systemFileName;
    }
    
    public String getUserFileName() {
        return userFileName;
    }
    
    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }
    
    public String getSystemFileLocation() {
        return systemFileLocation;
    }
    
    public void setSystemFileLocation(String systemFileLocation) {
        this.systemFileLocation = systemFileLocation;
    }
    
    public Byte getFileType() {
        return fileType;
    }
    
    public void setFileType(Byte fileType) {
        this.fileType = fileType;
    }
    
    public Byte getObjectType() {
        return objectType;
    }
    
    public void setObjectType(Byte objectType) {
        this.objectType = objectType;
    }
    
    public Byte getFileStatus() {
        return fileStatus;
    }
    
    public void setFileStatus(Byte fileStatus) {
        this.fileStatus = fileStatus;
    }
    
    public Date getProcessBeginDate() {
        return processBeginDate;
    }
    
    public void setProcessBeginDate(Date processBeginDate) {
        this.processBeginDate = processBeginDate;
    }
    
    public Date getProcessEndDate() {
        return processEndDate;
    }
    
    public void setProcessEndDate(Date processEndDate) {
        this.processEndDate = processEndDate;
    }
    
    public Job getImportJob() {
        return importJob;
    }
    
    public void setImportJob(Job importJob) {
        this.importJob = importJob;
    }
    
    public Date getFileCreationDate() {
        return fileCreationDate;
    }
    
    public void setFileCreationDate(Date fileCreationDate) {
        this.fileCreationDate = fileCreationDate;
    }
    
    public Date getFileUpdationDate() {
        return fileUpdationDate;
    }
    
    public void setFileUpdationDate(Date fileUpdationDate) {
        this.fileUpdationDate = fileUpdationDate;
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