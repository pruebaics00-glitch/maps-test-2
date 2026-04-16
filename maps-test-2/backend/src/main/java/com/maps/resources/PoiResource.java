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
    // ObjectMapper removed as part of Bolt optimization

    public PoiResource(PoiDAO poiDAO) {
        this.poiDAO = poiDAO;
    }

    @GET
    public Response getPois() {
        try {
            // ⚡ Bolt: Use PostGIS native JSON aggregation to retrieve the fully formatted GeoJSON string.
            // This eliminates Java-side list/map allocations, iteration, and Jackson parsing overhead,
            // returning the raw JSON string directly. Jackson won't add extraneous quotes due to @Produces(MediaType.APPLICATION_JSON).
            String geoJson = poiDAO.getPoisAsGeoJson();
            return Response.ok(geoJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
