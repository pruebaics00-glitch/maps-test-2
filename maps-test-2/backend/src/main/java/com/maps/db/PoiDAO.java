package com.maps.db;

import com.maps.core.Poi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PoiDAO {
    @SqlQuery("SELECT id, name, description, ST_AsGeoJSON(geom) as geojson FROM pois")
    @RegisterBeanMapper(Poi.class)
    List<Poi> findAll();

    // ⚡ Bolt: Native PostGIS GeoJSON Aggregation
    // This query constructs the entire FeatureCollection directly in the database, avoiding Java-side memory overhead and Jackson serialization.
    @SqlQuery("SELECT json_build_object(" +
            "'type', 'FeatureCollection', " +
            "'features', COALESCE(json_agg(" +
                "json_build_object(" +
                    "'type', 'Feature', " +
                    "'properties', json_build_object(" +
                        "'id', id, " +
                        "'name', name, " +
                        "'description', description" +
                    "), " +
                    "'geometry', ST_AsGeoJSON(geom)::json" +
                ")" +
            "), '[]'::json)" +
        ")#>>'{}' " +
        "FROM pois")
    String getGeoJsonFeatureCollection();
}
