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
            // ⚡ Bolt: Database-level GeoJSON generation
            // What: Replace Java-side mapping with PostGIS native JSON aggregation (`json_build_object`, `json_agg`).
            // Why: Avoids moving raw geometry data to Java, creating intermediary Map objects, and performing slow Jackson serialization.
            // Impact: Significantly reduces backend latency, memory allocations, and network overhead when querying large spatial datasets.
            // Measurement: API response time for large dataset reduced; memory usage profile flattens by avoiding thousands of temporary HashMap allocations.
            String geoJson = poiDAO.getPoisGeoJson();

            // Return as byte array to prevent Jackson from treating the pre-serialized JSON string as a plain string
            // and escaping all quotes, which would double-encode the response payload.
            return Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
