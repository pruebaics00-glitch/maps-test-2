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
            // ⚡ Bolt: Offload GeoJSON aggregation to PostGIS
            // What: Replaced Java-side iteration and mapping with native DB JSON aggregation.
            // Why: Avoids moving large datasets into Java memory, parsing GeoJSON, allocating Maps, and Jackson serialization.
            // Impact: Significant reduction in memory overhead and CPU usage. Faster response times for large datasets.
            // Measurement: Compare memory usage and response times for endpoints with thousands of POIs.
            String geoJson = poiDAO.getPoisGeoJson();

            // Return raw JSON bytes to bypass Jackson double-encoding
            return Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
