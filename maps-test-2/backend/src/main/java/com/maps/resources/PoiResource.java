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
            // ⚡ Bolt: Offload GeoJSON aggregation to PostGIS
            // What: Replaced Java-side object mapping and Jackson serialization with native PostGIS JSON aggregation.
            // Why: Avoids loading entire tables into Java heap memory and eliminates CPU-intensive Jackson tree manipulation.
            // Impact: Significantly reduces backend memory footprint and speeds up API response generation for large datasets.
            // Measurement: Compare backend heap usage and API response times (Time to First Byte) before and after.
            String geojsonString = poiDAO.getPoisGeoJSON();
            return Response.ok(geojsonString.getBytes(StandardCharsets.UTF_8)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
