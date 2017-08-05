package com.ez.portal.library.rest.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.library.request.LibraryObjectTypeRequest;
import com.ez.portal.library.response.LibraryObjectTypeResponse;

@WebService
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LibraryService {

    @GET
    @Path("get-library-object-type/{libraryObjectTypeId}")
    LibraryObjectTypeResponse getLibraryObjectType(@PathParam("libraryObjectTypeId") Long id) throws Exception;

    @GET
    @Path("get-all-library-object-types")
    LibraryObjectTypeResponse getAllLibraryObjectTypes() throws Exception;

    @POST
    @Path("create-library-object-type")
    LibraryObjectTypeResponse createLibraryObjectType(LibraryObjectTypeRequest request) throws Exception;

}
