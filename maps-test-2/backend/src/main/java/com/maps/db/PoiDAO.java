package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    // ⚡ Bolt: Offload GeoJSON aggregation to PostGIS to avoid memory overhead and manual processing in Java
    @SqlQuery("SELECT json_build_object(" +
              "  'type', 'FeatureCollection'," +
              "  'features', COALESCE(json_agg(" +
              "    json_build_object(" +
              "      'type', 'Feature'," +
              "      'geometry', ST_AsGeoJSON(geom)::json," +
              "      'properties', json_build_object(" +
              "        'id', id," +
              "        'name', name," +
              "        'description', description" +
              "      )" +
              "    )" +
              "  ), '[]'::json)" +
              ")::text FROM pois")
    String findAllAsGeoJson();
}
