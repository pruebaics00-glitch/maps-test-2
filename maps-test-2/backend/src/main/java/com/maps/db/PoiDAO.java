package com.maps.db;

import com.maps.core.Poi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PoiDAO {
    @SqlQuery("SELECT id, name, description, ST_AsGeoJSON(geom) as geojson FROM pois")
    @RegisterBeanMapper(Poi.class)
    List<Poi> findAll();

    // ⚡ Bolt Performance Optimization:
    // Native PostGIS JSON aggregation prevents large Java-side memory allocations
    // and eliminates N+1 deserialization/reserialization overhead.
    // Wrap in COALESCE to ensure '[]' is returned for empty sets instead of null.
    // Cast ST_AsGeoJSON to ::json to prevent it from being serialized as an escaped string.
    @SqlQuery("SELECT json_build_object(" +
              "'type', 'FeatureCollection'," +
              "'features', COALESCE(json_agg(" +
                  "json_build_object(" +
                      "'type', 'Feature'," +
                      "'properties', json_build_object(" +
                          "'id', id," +
                          "'name', name," +
                          "'description', description" +
                      ")," +
                      "'geometry', ST_AsGeoJSON(geom)::json" +
                  ")" +
              "), '[]'::json)" +
              ")::text FROM pois")
    String getGeoJsonFeatures();
}
