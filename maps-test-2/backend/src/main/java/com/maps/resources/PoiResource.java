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
            // We use PostGIS native JSON aggregation (json_build_object, json_agg)
            // to generate the entire FeatureCollection in the database.
            // This avoids creating temporary Java objects (like Lists and Maps) and
            // entirely bypasses the memory overhead and Jackson double-encoding serialization loops.
            String jsonString = poiDAO.getFeatureCollection();

            return Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
