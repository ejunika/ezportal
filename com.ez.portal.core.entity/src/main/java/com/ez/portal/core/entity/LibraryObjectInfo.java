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
@Table(name = "LIBRARY_OBJECT_INFO")
@XmlRootElement(name = "libraryObjectInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObjectInfo extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "LibraryObjectInfoIdGenerator")
    @GenericGenerator(name = "LibraryObjectInfoIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "LIBRARY_OBJECT_INFO_ID")
    private Long libraryObjectInfoId;
    
    @Column(name = "LIBRARY_OBJECT_INFO_NAME")
    private String libraryObjectInfoName;
    
    @Column(name = "LIBRARY_OBJECT_INFO_VALUE")
    private String libraryObjectInfoValue;

    @ManyToOne
    @JoinColumn(name = "LIBRARY_OBJECT_ID")
    private LibraryObject libraryObject;

    public Long getLibraryObjectInfoId() {
        return libraryObjectInfoId;
    }

    public void setLibraryObjectInfoId(Long libraryObjectInfoId) {
        this.libraryObjectInfoId = libraryObjectInfoId;
    }

    public LibraryObject getLibraryObject() {
        return libraryObject;
    }

    public void setLibraryObject(LibraryObject libraryObject) {
        this.libraryObject = libraryObject;
    }

    public String getLibraryObjectInfoName() {
        return libraryObjectInfoName;
    }

    public void setLibraryObjectInfoName(String libraryObjectInfoName) {
        this.libraryObjectInfoName = libraryObjectInfoName;
    }

    public String getLibraryObjectInfoValue() {
        return libraryObjectInfoValue;
    }

    public void setLibraryObjectInfoValue(String libraryObjectInfoValue) {
        this.libraryObjectInfoValue = libraryObjectInfoValue;
    }
    
}
