package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    // ⚡ Bolt: Offload GeoJSON building to PostGIS natively to prevent Java-side memory overhead and Jackson serialization.
    @SqlQuery("SELECT json_build_object(" +
              "'type', 'FeatureCollection'," +
              "'features', COALESCE(json_agg(" +
              "  json_build_object(" +
              "    'type', 'Feature'," +
              "    'geometry', ST_AsGeoJSON(geom)::json," +
              "    'properties', json_build_object(" +
              "      'id', id," +
              "      'name', name," +
              "      'description', description" +
              "    )" +
              "  )" +
              "), '[]'::json)) " +
              "FROM pois")
    String getGeoJson();
}
