package com.maps.resources;

import com.maps.core.Poi;
import com.maps.db.PoiDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/pois")
@Produces(MediaType.APPLICATION_JSON)
public class PoiResource {

    private final PoiDAO poiDAO;
    private final ObjectMapper mapper = new ObjectMapper();

    public PoiResource(PoiDAO poiDAO) {
        this.poiDAO = poiDAO;
    }

    @GET
    public Response getPois() {
        try {
            // ⚡ Bolt: Return pre-aggregated JSON string directly from DB
            // Why: Prevents N+1 allocations and Jackson serialization overhead
            // Impact: O(1) Java memory usage regardless of dataset size
            String geoJsonString = poiDAO.findAllAsGeoJson();
            return Response.ok(geoJsonString).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
