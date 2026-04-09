package com.maps.resources;

import com.maps.db.PoiDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
            // ⚡ Bolt: Performance improvement
            // Leveraging PostGIS native JSON aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON`)
            // directly in JDBI SQL query instead of mapping Java objects and Jackson serialization.
            // This eliminates Java-side memory overhead and returns the JSON string directly.
            String featureCollectionJson = poiDAO.getFeatureCollectionAsJson();
            return Response.ok(featureCollectionJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
