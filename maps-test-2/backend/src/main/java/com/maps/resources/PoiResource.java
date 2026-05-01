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
            // ⚡ Bolt: Performance Optimization
            // Instead of fetching rows, parsing PostGIS string GeoJSON in Java,
            // building a hierarchy of HashMaps, and serializing via Jackson,
            // we let PostGIS do native JSON aggregation in the database.
            // We retrieve the pre-built JSON string and return it as a byte array
            // to bypass Jackson serialization double-encoding.
            String geoJsonStr = poiDAO.getGeoJson();
            return Response.ok(geoJsonStr.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
