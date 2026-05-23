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
            // ⚡ Bolt: Native DB JSON aggregation
            // Shifted GeoJSON construction to PostGIS to eliminate Java-side object
            // instantiation overhead and memory spikes. The DB directly returns a
            // complete GeoJSON string. We return it as a byte array to bypass
            // Jackson double-encoding the raw string.
            String featureCollectionJson = poiDAO.getFeatureCollection();

            return Response.ok(featureCollectionJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
