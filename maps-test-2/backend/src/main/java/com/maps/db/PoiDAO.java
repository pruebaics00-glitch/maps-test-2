package com.maps.db;

import com.maps.core.Poi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PoiDAO {
    @SqlQuery("SELECT id, name, description, ST_AsGeoJSON(geom) as geojson FROM pois")
    @RegisterBeanMapper(Poi.class)
    List<Poi> findAll();

    // ⚡ Bolt Optimization: Offload GeoJSON construction to PostGIS native JSON aggregation.
    // This removes O(n) memory overhead in Java and bypasses Jackson serialization entirely.
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
              ")::text " +
              "FROM pois")
    String getFeatureCollection();
}
