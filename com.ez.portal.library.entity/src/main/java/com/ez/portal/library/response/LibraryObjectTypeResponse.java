package com.ez.portal.library.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.response.AbstractResponse;
import com.ez.portal.library.entity.LibraryObjectType;

@XmlRootElement(name = "libraryObjectTypeResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryObjectTypeResponse extends AbstractResponse {

    private static final long serialVersionUID = 1L;
    
    private List<LibraryObjectType> libraryObjectTypes;

    public List<LibraryObjectType> getLibraryObjectTypes() {
        return libraryObjectTypes;
    }

    public void setLibraryObjectTypes(List<LibraryObjectType> libraryObjectTypes) {
        this.libraryObjectTypes = libraryObjectTypes;
    }

}
