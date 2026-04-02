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
            // Bolt: Performance optimization
            // Instead of fetching POIs from database and processing them into Java objects
            // using Jackson, we use PostGIS to build the GeoJSON response at the database
            // layer. This saves Java heap space and JSON serialization/deserialization time.
            String geojson = poiDAO.getPoisGeoJSON();
            return Response.ok(geojson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
