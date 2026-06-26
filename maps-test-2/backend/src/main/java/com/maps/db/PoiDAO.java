package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    /**
     * ⚡ Bolt Performance Optimization
     * 💡 What: Use native PostGIS JSON aggregation (`json_build_object`, `json_agg`) to build the GeoJSON FeatureCollection directly in the database.
     * 🎯 Why: Eliminates the memory overhead of instantiating Java `Poi` objects and the CPU cost of Jackson serialization.
     * 📊 Impact: Significantly reduces backend memory allocation per request and eliminates Jackson serialization latency.
     * 🔬 Measurement: Verify API response times and backend memory usage under load before and after the change.
     */
    @SqlQuery("SELECT json_build_object(" +
            "'type', 'FeatureCollection'," +
            "'features', COALESCE(json_agg(" +
                "json_build_object(" +
                    "'type', 'Feature'," +
                    "'geometry', ST_AsGeoJSON(geom)::json," +
                    "'properties', json_build_object(" +
                        "'id', id," +
                        "'name', name," +
                        "'description', description" +
                    ")" +
                ")" +
            "), '[]'::json)" +
            ")::text FROM pois")
    String getFeatureCollection();
}
