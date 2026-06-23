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
            // ⚡ Bolt: Offloaded GeoJSON aggregation to PostGIS
            // What: Replaced Java-side object mapping and iteration with PostGIS native JSON aggregation.
            // Why: Avoids large amounts of object allocations on the Java heap, reducing GC pressure and bypassing Jackson serialization.
            // Impact: Greatly reduces memory footprint and CPU overhead for large datasets.
            // Measurement: Monitor backend heap usage and garbage collection times, as well as API response time.
            String geoJson = poiDAO.getPoisAsGeoJson();
            return Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
