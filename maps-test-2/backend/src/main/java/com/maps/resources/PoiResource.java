package com.maps.resources;

import com.maps.core.Poi;
import com.maps.db.PoiDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            // Bolt: [performance improvement] Offload GeoJSON generation to PostGIS
            // Instead of loading all records into Java memory, building maps/lists,
            // and relying on Jackson serialization, we use PostGIS's json_build_object
            // and json_agg directly in the SQL query.
            // This reduces memory pressure and serialization overhead, returning the
            // result as a byte array to bypass Jackson completely.
            String geoJsonString = poiDAO.getPoisAsGeoJson();
            return Response.ok(geoJsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
