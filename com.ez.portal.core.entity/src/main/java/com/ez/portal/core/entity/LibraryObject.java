package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LIBRARY_OBJECT")
@XmlRootElement(name = "libraryObject")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObject extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "LibraryObjectIdGenerator")
    @GenericGenerator(name = "LibraryObjectIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "LIBRARY_OBJECT_ID")
    private Long libraryObjectId;
    
    @Column(name = "LIBRARY_OBJECT_TYPE")
    private Byte libraryObjectType;
    
    @Column(name = "LIBRARY_OBJECT_NAME")
    private String libraryObjectName;

    public Long getLibraryObjectId() {
        return libraryObjectId;
    }

    public void setLibraryObjectId(Long libraryObjectId) {
        this.libraryObjectId = libraryObjectId;
    }

    public Byte getLibraryObjectType() {
        return libraryObjectType;
    }

    public void setLibraryObjectType(Byte libraryObjectType) {
        this.libraryObjectType = libraryObjectType;
    }

    public String getLibraryObjectName() {
        return libraryObjectName;
    }

    public void setLibraryObjectName(String libraryObjectName) {
        this.libraryObjectName = libraryObjectName;
    }

}
