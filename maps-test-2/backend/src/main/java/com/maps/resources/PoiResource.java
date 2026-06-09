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
            // ⚡ Bolt: Offloaded GeoJSON FeatureCollection generation to PostGIS native JSON aggregation.
            // This bypasses Java-side memory overhead (creating hundreds of HashMaps and Poi objects)
            // and Jackson serialization CPU costs by directly returning a raw JSON string from DB.
            String geojson = poiDAO.findAllAsGeoJson();
            return Response.ok(geojson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
