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
            // What: Get pre-computed GeoJSON string from PostGIS and return it directly as a byte array.
            // Why: Avoids double-encoding of the raw JSON string by Jackson and skips Java-side serialization completely.
            // Impact: Dramatically reduces CPU and memory usage when handling thousands of features.
            String featureCollectionJson = poiDAO.getFeatureCollection();

            // Note: Returning byte[] to prevent Jackson from treating the JSON string as a regular String
            // and escaping it with double quotes.
            return Response.ok(featureCollectionJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
