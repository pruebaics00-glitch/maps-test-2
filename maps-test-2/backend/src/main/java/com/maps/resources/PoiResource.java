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
            // What: Offload GeoJSON FeatureCollection generation to PostGIS and stream raw JSON directly.
            // Why: Avoids iterating through features in Java, prevents Jackson from parsing/re-serializing ST_AsGeoJSON strings, and drastically reduces memory allocations.
            // Impact: Significantly reduces JVM heap allocation and GC pressure; speeds up response times for large POI lists.
            // Measurement: Can be measured via /api/pois endpoint latency and memory profiling.
            String geoJson = poiDAO.getPoisFeatureCollection();
            return Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
