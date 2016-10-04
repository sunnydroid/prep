package com.sunny.webServices.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunshah on 10/1/16.
 */
@Path("/images")
public class ImageServlet {

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Map imageSearch(@QueryParam("size") @DefaultValue("300") final String size) {

        Map<String, Object> response = new HashMap<>();
        response.put("response", 300);

        return response;
    }
}
