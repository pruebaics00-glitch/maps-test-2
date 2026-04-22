package com.maps.db;

import com.maps.core.Poi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PoiDAO {
    // ⚡ Bolt Performance Optimization:
    // What: Replace Java-side JSON construction with native PostGIS JSON aggregation.
    // Why: Avoids loading potentially thousands of Java Objects into memory and eliminates Jackson serialization overhead.
    // Impact: Significantly reduces backend memory footprint and speeds up serialization, turning O(N) operations into O(1) query.
    @SqlQuery("SELECT json_build_object(" +
              "'type', 'FeatureCollection'," +
              "'features', COALESCE(json_agg(" +
              "  json_build_object(" +
              "    'type', 'Feature'," +
              "    'properties', json_build_object('id', id, 'name', name, 'description', description)," +
              "    'geometry', ST_AsGeoJSON(geom)::json" +
              "  )" +
              "), '[]'::json)" +
              ")::text FROM pois")
    String getFeatureCollection();
}
