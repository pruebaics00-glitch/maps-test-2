package com.maps.resources;

import com.maps.core.Poi;
import com.maps.db.PoiDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    // Removed ObjectMapper since we are bypassing Jackson serialization

    public PoiResource(PoiDAO poiDAO) {
        this.poiDAO = poiDAO;
    }

    @GET
    public Response getPois() {
        try {
            // ⚡ Bolt: Offload GeoJSON serialization to PostGIS
            // What: Using PostGIS native JSON aggregation to build FeatureCollection directly in SQL
            // Why: Avoids Java object instantiation overhead and Jackson serialization overhead
            // Impact: Reduces memory usage and response time for large datasets
            // Measurement: API response time and application memory profiling

            String geoJsonString = poiDAO.getPoisAsGeoJson();

            // Return as byte array to bypass Jackson double-encoding a raw string
            return Response.ok(geoJsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
