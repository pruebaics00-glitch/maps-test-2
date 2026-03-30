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
            // ⚡ Bolt: Use direct DB JSON generation
            // Why: Previously, this endpoint parsed GeoJSON strings for every row into Jackson JsonNodes
            // and allocated massive Map/List trees in Java memory before serializing back to JSON.
            // Impact: Completely eliminates Java memory usage scaling with row count and prevents
            // Jackson parsing bottlenecks. Replaced with O(1) String return.
            String featureCollectionJson = poiDAO.getFeatureCollection();

            return Response.ok(featureCollectionJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
