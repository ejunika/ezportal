package com.ez.portal.library.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.library.entity.LibraryObjectType;

@XmlRootElement(name = "libraryObjectTypeRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObjectTypeRequest {
    
    private LibraryObjectType libraryObjectType;

    public LibraryObjectType getLibraryObjectType() {
        return libraryObjectType;
    }

    public void setLibraryObjectType(LibraryObjectType libraryObjectType) {
        this.libraryObjectType = libraryObjectType;
    }
    
}
