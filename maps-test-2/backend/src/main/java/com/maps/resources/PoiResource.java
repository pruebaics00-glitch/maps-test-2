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
            // ⚡ Bolt: Offloaded JSON serialization and spatial processing to PostGIS
            // Why: Avoids Java memory overhead of creating objects and Jackson serialization for large spatial datasets
            // Impact: Significantly reduces backend memory usage and GC pressure, speeding up API response times
            // Measurement: API response time under load and heap memory usage should be lower
            String featureCollection = poiDAO.getFeatureCollection();
            return Response.ok(featureCollection.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
