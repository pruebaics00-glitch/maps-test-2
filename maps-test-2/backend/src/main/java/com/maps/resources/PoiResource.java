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
            // ⚡ Bolt: Direct DB-to-JSON
            // The database constructs the entire GeoJSON payload as a single string.
            // This eliminates Jackson ObjectMapper serialization, object instantiations,
            // and mapping overhead in Java, providing optimal response time and memory usage.
            String geoJson = poiDAO.getPoisAsGeoJson();

            // Handle empty case
            if (geoJson == null) {
                return Response.ok("{\"type\": \"FeatureCollection\", \"features\": []}").build();
            }

            return Response.ok(geoJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
