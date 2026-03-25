package com.maps.resources;

import com.maps.core.Poi;
import com.maps.db.PoiDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.RawValue;
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
            List<Poi> pois = poiDAO.findAll();

            Map<String, Object> featureCollection = new HashMap<>();
            featureCollection.put("type", "FeatureCollection");

            List<Map<String, Object>> features = new ArrayList<>();

            for (Poi poi : pois) {
                Map<String, Object> feature = new HashMap<>();
                feature.put("type", "Feature");

                Map<String, Object> properties = new HashMap<>();
                properties.put("id", poi.getId());
                properties.put("name", poi.getName());
                properties.put("description", poi.getDescription());

                feature.put("properties", properties);
                // ⚡ Bolt: Prevent unnecessary parsing of JSON by using RawValue
                feature.put("geometry", new RawValue(poi.getGeojson()));

                features.add(feature);
            }

            featureCollection.put("features", features);
            return Response.ok(featureCollection).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
