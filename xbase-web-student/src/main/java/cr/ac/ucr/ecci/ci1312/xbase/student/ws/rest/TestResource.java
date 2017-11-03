package cr.ac.ucr.ecci.ci1312.xbase.student.ws.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Utility Web Service for testing purposes.
 *
 * @author Rodrigo A. Bartels
 */
@Component
@Scope("request")
@Path("/util")
public class TestResource{

    private static final Logger logger = LoggerFactory.getLogger(TestResource.class);

    /**
     * Silly endpoint that gets an id in the request query param and returns it.
     *
     * @return a HTTP status of OK if all the ids were successfully processed.
     */
    @GET
    @Path("/test")
    public Response testSimpleGet(){
        return Response.ok().build();
    }

    /**
     * Silly endpoint that gets an id in the path and returns it.
     *
     * @param id the user's id
     *
     * @return a HTTP status of OK if all the ids were successfully processed.
     */
    @GET
    @Path("/{id}/testPath")
    public Response testPath(@PathParam("id") String id){
        if(id == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok().entity(id).build();
    }
    
    /**
     * Silly endpoint that gets an id in the request query param and returns it.
     *
     * @param id the user's id
     *
     * @return a HTTP status of OK if all the ids were successfully processed.
     */
    @GET
    @Path("/testQuery")
    public Response testQueryParams(@QueryParam("id") String id){
        if(id == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        
        return Response.ok().entity(id).build();
    }

    /**
     * Silly endpoint that gets an id in the request query param and returns it.
     *
     * @return a HTTP status of OK if all the ids were successfully processed.
     */
    @POST
    @Path("/test")
    public Response testSimplePost(@FormParam("id") String id){
        if(id == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok().entity(id).build();
    }
}
