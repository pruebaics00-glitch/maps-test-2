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
            // ⚡ Bolt: Offloaded JSON FeatureCollection generation to PostGIS database
            // Why: Eliminates O(N) Java allocations for maps, lists, and Jackson object mapping per request.
            // Impact: Significantly reduces memory footprint and CPU usage during serialization.
            // Measurement: API response time under high load should decrease, lower GC pressure.
            String featureCollectionJson = poiDAO.getFeatureCollection();

            // Dropwizard/JAX-RS annotated with @Produces(MediaType.APPLICATION_JSON)
            // correctly returns raw JSON strings without extraneous quotes.
            return Response.ok(featureCollectionJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
