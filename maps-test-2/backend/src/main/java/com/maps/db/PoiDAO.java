package com.maps.db;

import com.maps.core.Poi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PoiDAO {
    @SqlQuery("SELECT id, name, description, ST_AsGeoJSON(geom) as geojson FROM pois")
    @RegisterBeanMapper(Poi.class)
    List<Poi> findAll();

    // ⚡ Bolt: Native PostGIS GeoJSON generation
    // Why: Bypasses Java-side memory overhead and Jackson tree manipulation by offloading JSON construction to the DB
    @SqlQuery("SELECT json_build_object(" +
              "'type', 'FeatureCollection', " +
              "'features', COALESCE(json_agg(" +
              "  json_build_object(" +
              "    'type', 'Feature', " +
              "    'geometry', CAST(ST_AsGeoJSON(geom) AS json), " +
              "    'properties', json_build_object(" +
              "      'id', id, " +
              "      'name', name, " +
              "      'description', description" +
              "    )" +
              "  )" +
              "), '[]'::json)" +
              ")::text FROM pois")
    String findAllAsGeoJson();
}
