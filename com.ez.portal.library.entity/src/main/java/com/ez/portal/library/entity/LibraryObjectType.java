package com.ez.portal.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.ez.portal.core.entity.AbstractEntity;

@Entity
@Table(name = "LIBRARY_OBJECT_TYPE")
@XmlRootElement(name = "libraryObjectType")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObjectType extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "LibraryObjectTypeIdGenerator")
    @GenericGenerator(name = "LibraryObjectTypeIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "LIBRARY_OBJECT_TYPE_ID")
    private Long libraryObjectTypeId;
    
    @Column(name = "LIBRARY_OBJECT_TYPE_NAME")
    private String libraryObjectTypeName;

    public Long getLibraryObjectTypeId() {
        return libraryObjectTypeId;
    }

    public void setLibraryObjectTypeId(Long libraryObjectTypeId) {
        this.libraryObjectTypeId = libraryObjectTypeId;
    }

    public String getLibraryObjectTypeName() {
        return libraryObjectTypeName;
    }

    public void setLibraryObjectTypeName(String libraryObjectTypeName) {
        this.libraryObjectTypeName = libraryObjectTypeName;
    }

}
