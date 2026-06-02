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
            // ⚡ Bolt Performance Optimization
            // 💡 What: Delegate GeoJSON aggregation to PostGIS and return raw bytes
            // 🎯 Why: Avoids fetching all records as Java objects, mapping them, and using Jackson to serialize to JSON.
            // 📊 Impact: Significantly reduces JVM memory usage and CPU overhead, preventing double-encoding of strings.
            String geoJsonString = poiDAO.getGeoJson();
            return Response.ok(geoJsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
