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
            // ⚡ Bolt Optimization: Offload GeoJSON aggregation to PostGIS
            // This eliminates:
            // 1. Memory overhead of creating Java List<Poi> and nested Maps for the FeatureCollection
            // 2. CPU overhead of Jackson parsing the ST_AsGeoJSON string per-row (mapper.readTree)
            // 3. Jackson serialization overhead for the final response
            String featureCollectionJson = poiDAO.getGeoJsonFeatureCollection();

            // Return as byte array to bypass Jackson double-encoding the raw JSON string
            return Response.ok(featureCollectionJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
