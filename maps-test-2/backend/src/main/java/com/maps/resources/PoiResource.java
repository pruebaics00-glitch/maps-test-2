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

    /**
     * ⚡ Bolt Performance Optimization
     * 💡 What: Retrieve pre-built GeoJSON directly from the database and return it as a byte array.
     * 🎯 Why: Bypasses the memory overhead of Java-side looping and prevents Jackson from double-encoding the pre-built JSON string.
     * 📊 Impact: Improves API response time and reduces CPU usage during serialization.
     * 🔬 Measurement: Verify API response payload is correctly formatted GeoJSON and response times are reduced.
     */
    @GET
    public Response getPois() {
        try {
            String featureCollectionJson = poiDAO.getFeatureCollection();
            return Response.ok(featureCollectionJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
