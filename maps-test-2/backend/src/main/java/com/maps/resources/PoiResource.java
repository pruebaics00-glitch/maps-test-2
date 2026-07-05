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
            // Delegate GeoJSON construction to PostGIS using json_build_object and json_agg.
            // This avoids creating numerous Java POJOs and Map objects in memory,
            // and skips the expensive Jackson serialization process.
            String featureCollectionJson = poiDAO.getGeoJsonPois();

            // Return as byte array to bypass Jackson double-encoding the raw JSON string
            return Response.ok(featureCollectionJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
