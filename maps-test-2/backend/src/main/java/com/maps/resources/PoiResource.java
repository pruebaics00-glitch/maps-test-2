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
            // Bolt: [performance improvement]
            // What: Using PostGIS native JSON aggregation instead of manual Java construction
            // Why: Avoids iterating through all database rows in Java, avoids Jackson parsing of ST_AsGeoJSON per row, avoids building large HashMap structures in memory
            // Impact: Significant reduction in heap allocations, less GC pressure, and faster API response times due to delegating the payload construction to the database
            // Measurement: API response payload size remains identical, but server-side execution time drops significantly with large POI counts
            String geoJson = poiDAO.getPoisAsGeoJson();
            return Response.ok(geoJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
