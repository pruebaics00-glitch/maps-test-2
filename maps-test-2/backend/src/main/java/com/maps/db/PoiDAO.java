package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    // ⚡ Bolt: Offload JSON aggregation and geometry formatting to PostGIS.
    // This avoids fetching individual rows into Java memory and instantiating POI objects,
    // reducing memory overhead and garbage collection pauses.
    @SqlQuery("SELECT json_build_object(" +
            "'type', 'FeatureCollection'," +
            "'features', COALESCE(" +
            "json_agg(" +
            "json_build_object(" +
            "'type', 'Feature'," +
            "'geometry', ST_AsGeoJSON(geom)::json," +
            "'properties', json_build_object(" +
            "'id', id," +
            "'name', name," +
            "'description', description" +
            ")" +
            ")" +
            ")," +
            "'[]'::json" +
            ")" +
            ")::text FROM pois")
    String getPoisGeoJSON();
}
