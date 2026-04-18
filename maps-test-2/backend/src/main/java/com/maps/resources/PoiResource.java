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
            // Performance Optimization: Offload GeoJSON generation to PostGIS.
            // This avoids creating numerous maps and objects in memory and avoids Jackson parsing
            // the ST_AsGeoJSON string. PostGIS creates the entire FeatureCollection natively.
            String geoJson = poiDAO.getPoisAsGeoJson();
            // Return raw bytes to bypass Jackson string serialization and double-encoding
            byte[] rawJsonBytes = geoJson != null ? geoJson.getBytes(java.nio.charset.StandardCharsets.UTF_8) : "{}".getBytes(java.nio.charset.StandardCharsets.UTF_8);
            return Response.ok(rawJsonBytes).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
