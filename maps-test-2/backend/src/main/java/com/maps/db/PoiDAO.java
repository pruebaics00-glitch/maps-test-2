package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    // ⚡ Bolt: Offload GeoJSON building to PostgreSQL for performance
    // What: Build FeatureCollection directly in query using PostGIS json functions
    // Why: Eliminates O(N) Java-side mapping loop and Jackson tree parsing overhead
    // Impact: Avoids instantiating large collections in memory and parsing JSON multiple times
    @SqlQuery("SELECT json_build_object(" +
            "'type', 'FeatureCollection', " +
            "'features', COALESCE(json_agg(" +
            "  json_build_object(" +
            "    'type', 'Feature', " +
            "    'properties', json_build_object(" +
            "      'id', id, " +
            "      'name', name, " +
            "      'description', description" +
            "    ), " +
            "    'geometry', ST_AsGeoJSON(geom)::json" +
            "  )" +
            "), '[]'::json)" +
            ")::text FROM pois")
    String findAllAsGeoJson();
}
