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
            // ⚡ Bolt: Native PostGIS JSON aggregation
            // Why: Replaces expensive Java-side Jackson mapping and memory overhead with direct DB-side JSON aggregation.
            // Impact: Eliminates N+1 object creations, reduces memory usage, and speeds up serialization significantly.
            // Measurement: API response time is reduced by delegating all JSON building to PostgreSQL.
            String geoJson = poiDAO.getGeoJson();
            return Response.ok(geoJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
