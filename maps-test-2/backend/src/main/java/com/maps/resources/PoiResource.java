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
            // ⚡ Bolt: Offloaded GeoJSON generation to PostGIS
            // Why: Prevents N+1 parsing of ST_AsGeoJSON strings and massive JVM memory overhead from allocating maps/lists for every feature.
            // Impact: Completely bypasses Jackson overhead, returning bytes directly. Scales linearly with DB instead of exponentially with JVM heap.
            // Measurement: Compare CPU usage and GC pauses during load test of /api/pois with thousands of points.
            String geoJson = poiDAO.getPoisGeoJSON();
            return Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
