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
@Table(name = "LIBRARY_OBJECT_BORROW_REQUEST")
@XmlRootElement(name = "libraryObjectBorrowRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObjectBorrowRequest extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "LibraryObjectBorrowRequestIdGenerator")
    @GenericGenerator(name = "LibraryObjectBorrowRequestIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "LIBRARY_OBJECT_BORROW_REQUEST_ID")
    private Long libraryObjectBorrowRequestId;
    
    public Long getLibraryObjectBorrowRequestId() {
        return libraryObjectBorrowRequestId;
    }

    public void setLibraryObjectBorrowRequestId(Long libraryObjectBorrowRequestId) {
        this.libraryObjectBorrowRequestId = libraryObjectBorrowRequestId;
    }

}
