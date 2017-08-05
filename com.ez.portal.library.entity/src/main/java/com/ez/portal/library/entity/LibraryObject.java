package com.ez.portal.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.ez.portal.core.entity.AbstractEntity;

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
    
    @OneToOne
    @JoinColumn(name = "LIBRARY_OBJECT_TYPE_ID")
    private LibraryObjectType libraryObjectType;
    
    @Column(name = "LIBRARY_OBJECT_NAME")
    private String libraryObjectName;
    
    @ManyToOne
    @JoinColumn(name = "LIBRARY_OBJECT_BORROW_REQUEST_ID")
    private LibraryObjectBorrowRequest libraryObjectBorrowRequest;

    public Long getLibraryObjectId() {
        return libraryObjectId;
    }

    public void setLibraryObjectId(Long libraryObjectId) {
        this.libraryObjectId = libraryObjectId;
    }

    public String getLibraryObjectName() {
        return libraryObjectName;
    }

    public void setLibraryObjectName(String libraryObjectName) {
        this.libraryObjectName = libraryObjectName;
    }

    public LibraryObjectBorrowRequest getLibraryObjectBorrowRequest() {
        return libraryObjectBorrowRequest;
    }

    public void setLibraryObjectBorrowRequest(LibraryObjectBorrowRequest libraryObjectBorrowRequest) {
        this.libraryObjectBorrowRequest = libraryObjectBorrowRequest;
    }

    public LibraryObjectType getLibraryObjectType() {
        return libraryObjectType;
    }

    public void setLibraryObjectType(LibraryObjectType libraryObjectType) {
        this.libraryObjectType = libraryObjectType;
    }

}
