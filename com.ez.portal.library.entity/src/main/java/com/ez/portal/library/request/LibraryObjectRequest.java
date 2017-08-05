package com.ez.portal.library.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.library.entity.LibraryObject;

@XmlRootElement(name = "libraryObjectRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObjectRequest {
    
    private LibraryObject libraryObject;

    public LibraryObject getLibraryObject() {
        return libraryObject;
    }

    public void setLibraryObject(LibraryObject libraryObject) {
        this.libraryObject = libraryObject;
    }
    
}
