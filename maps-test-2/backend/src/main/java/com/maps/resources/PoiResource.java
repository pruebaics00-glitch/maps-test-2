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

    /* ⚡ Bolt: Native DB JSON Aggregation
     * What: Moved GeoJSON FeatureCollection generation into PostGIS query using `json_build_object` and `json_agg`.
     * Why: Prevents reading raw records into memory (list of Poi objects), creating large Maps/Lists overhead, and removes the need for Jackson JSON parsing on the geometry.
     * Impact: Reduces GC pressure, memory footprint, and CPU processing time on the server, resulting in faster API response times.
     * Measurement: Check heap usage and API latency when fetching points.
     */
    @GET
    public Response getPois() {
        try {
            String featureCollectionJson = poiDAO.getFeatureCollection();
            return Response.ok(featureCollectionJson).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
