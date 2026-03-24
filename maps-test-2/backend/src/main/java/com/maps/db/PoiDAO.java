package com.maps.db;

import com.maps.core.Poi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PoiDAO {
    @SqlQuery("SELECT id, name, description, ST_AsGeoJSON(geom) as geojson FROM pois")
    @RegisterBeanMapper(Poi.class)
    List<Poi> findAll();

    // ⚡ Bolt: Native PostGIS JSON aggregation
    // Constructs the complete GeoJSON FeatureCollection in the database
    // Eliminates Java-side iteration, memory allocation for intermediate structures, and Jackson parsing
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
              ")::text " +
              "FROM pois")
    String getPoisAsGeoJson();
}
