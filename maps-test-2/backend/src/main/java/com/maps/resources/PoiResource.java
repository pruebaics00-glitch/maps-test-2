package com.maps.resources;

import com.maps.core.Poi;
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
            // Optimization: Let Postgres construct the GeoJSON using json_agg and ST_AsGeoJSON.
            // This prevents Java-side memory overhead of iterating through Poi objects,
            // building hashmaps, and parsing JSON via Jackson.
            String geoJsonString = poiDAO.getGeoJson();

            // Return string as byte array to bypass Jackson double-escaping strings in @Produces(APPLICATION_JSON) endpoints.
            return Response.ok(geoJsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
