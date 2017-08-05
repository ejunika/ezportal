package com.ez.portal.library.core.dao.manager;

import com.ez.portal.library.dao.intf.LibraryObjectBorrowRequestDAO;
import com.ez.portal.library.dao.intf.LibraryObjectDAO;
import com.ez.portal.library.dao.intf.LibraryObjectInfoDAO;
import com.ez.portal.library.dao.intf.LibraryObjectTypeDAO;

public class LibraryCoreDAOManager {
    
    private LibraryObjectTypeDAO libraryObjectTypeDAO;
    
    private LibraryObjectDAO libraryObjectDAO;
    
    private LibraryObjectInfoDAO libraryObjectInfoDAO;
    
    private LibraryObjectBorrowRequestDAO libraryObjectBorrowRequestDAO;

    public LibraryObjectTypeDAO getLibraryObjectTypeDAO() {
        return libraryObjectTypeDAO;
    }

    public void setLibraryObjectTypeDAO(LibraryObjectTypeDAO libraryObjectTypeDAO) {
        this.libraryObjectTypeDAO = libraryObjectTypeDAO;
    }

    public LibraryObjectDAO getLibraryObjectDAO() {
        return libraryObjectDAO;
    }

    public void setLibraryObjectDAO(LibraryObjectDAO libraryObjectDAO) {
        this.libraryObjectDAO = libraryObjectDAO;
    }

    public LibraryObjectInfoDAO getLibraryObjectInfoDAO() {
        return libraryObjectInfoDAO;
    }

    public void setLibraryObjectInfoDAO(LibraryObjectInfoDAO libraryObjectInfoDAO) {
        this.libraryObjectInfoDAO = libraryObjectInfoDAO;
    }

    public LibraryObjectBorrowRequestDAO getLibraryObjectBorrowRequestDAO() {
        return libraryObjectBorrowRequestDAO;
    }

    public void setLibraryObjectBorrowRequestDAO(LibraryObjectBorrowRequestDAO libraryObjectBorrowRequestDAO) {
        this.libraryObjectBorrowRequestDAO = libraryObjectBorrowRequestDAO;
    }
    
}
