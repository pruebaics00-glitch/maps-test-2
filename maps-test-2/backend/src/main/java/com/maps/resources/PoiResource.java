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
            // ⚡ Bolt Performance Optimization:
            // Switched from Java-side map allocations and Jackson serialization
            // to native PostGIS JSON aggregation.
            String jsonString = poiDAO.getGeoJsonFeatures();

            // Return as byte array to bypass Jackson serialization and prevent
            // the JSON string from being double-encoded.
            return Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
