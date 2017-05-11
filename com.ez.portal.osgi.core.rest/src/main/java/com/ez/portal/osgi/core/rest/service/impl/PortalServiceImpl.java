package com.ez.portal.osgi.core.rest.service.impl;

import javax.ws.rs.core.Response;

import com.ez.portal.rest.service.PortalService;

public class PortalServiceImpl implements PortalService {

    @Override
    public Response get(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response post(String reqData) {
        // TODO Auto-generated method stub
        return Response.ok("{\"success\": true}").status(Response.Status.OK).build();
    }

}
