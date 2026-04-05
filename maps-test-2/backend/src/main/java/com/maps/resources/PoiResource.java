package com.maps.resources;

import com.maps.core.Poi;
import com.maps.db.PoiDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/pois")
@Produces(MediaType.APPLICATION_JSON)
public class PoiResource {

    private final PoiDAO poiDAO;
    private final ObjectMapper mapper = new ObjectMapper();

    public PoiResource(PoiDAO poiDAO) {
        this.poiDAO = poiDAO;
    }

    @GET
    public Response getPois() {
        try {
            // ⚡ Bolt Performance Optimization:
            // What: Using PostGIS native JSON aggregation instead of Java-side Jackson mapping
            // Why: Avoids large memory overhead of loading rows into Java objects and converting them back to a giant JSON map
            // Impact: Significantly reduces GC pressure and speeds up response time by shifting work to the DB
            String geoJsonCollection = poiDAO.getFeatureCollection();

            // Dropwizard/JAX-RS returns raw JSON string directly without adding extraneous quotes
            return Response.ok(geoJsonCollection).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
