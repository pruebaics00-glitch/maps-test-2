package com.maps.resources;

import com.maps.db.PoiDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

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
            // ⚡ Bolt: Fetch pre-computed GeoJSON directly from database
            String geoJson = poiDAO.findAllAsGeoJson();

            // ⚡ Bolt: Return raw string as bytes to bypass Jackson double-encoding
            return Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            Map<String, String> error = Collections.singletonMap("error", e.getMessage());
            return Response.serverError().entity(error).build();
        }
    }
}
