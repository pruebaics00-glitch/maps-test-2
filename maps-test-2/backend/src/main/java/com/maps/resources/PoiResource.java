package com.maps.resources;

import com.maps.db.PoiDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;

@Path("/api/pois")
@Produces(MediaType.APPLICATION_JSON)
public class PoiResource {

    private final PoiDAO poiDAO;

    public PoiResource(PoiDAO poiDAO) {
        this.poiDAO = poiDAO;
    }

    @GET
    public Response getPois() {
        try {
            // Bolt Performance Optimization:
            // Fetch pre-aggregated GeoJSON directly from PostGIS instead of loading
            // individual POIs into Java memory and serializing them with Jackson.
            String jsonString = poiDAO.findAll();

            // Return raw string as bytes to bypass Jackson double-encoding when
            // returning a String from an @Produces(APPLICATION_JSON) endpoint.
            return Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
