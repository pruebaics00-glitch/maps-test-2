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
            // What: Shifted GeoJSON generation to PostgreSQL/PostGIS.
            // Why: Avoids fetching entire table into Java memory, creating POJO objects, and using Jackson to serialize to GeoJSON.
            // Impact: Vastly reduces Java memory footprint, minimizes DB network payload, and skips expensive Jackson serialization.
            String featureCollection = poiDAO.getFeatureCollection();
            return Response.ok(featureCollection.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
