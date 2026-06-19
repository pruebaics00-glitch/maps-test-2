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
            /*
             * ⚡ Bolt: Bypass Jackson Serialization
             * 💡 What: Returns raw JSON string as UTF-8 byte array
             * 🎯 Why: Bypasses Jackson serialization (which would double-encode the string) to serve pre-computed PostGIS JSON directly.
             * 📊 Impact: Eliminates serialization overhead completely.
             * 🔬 Measurement: Benchmarked API response times under load.
             */
            String geoJson = poiDAO.getGeoJson();
            return Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
