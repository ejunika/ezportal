package com.ez.portal.library.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.response.AbstractResponse;
import com.ez.portal.library.entity.LibraryObject;

@XmlRootElement(name = "libraryObjectResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObjectResponse extends AbstractResponse {

    private static final long serialVersionUID = 1L;
    
    private List<LibraryObject> libraryObjects;

    public List<LibraryObject> getLibraryObjects() {
        return libraryObjects;
    }

    public void setLibraryObjects(List<LibraryObject> libraryObjects) {
        this.libraryObjects = libraryObjects;
    }

}
