package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    // ⚡ Bolt: Offloaded JSON parsing to database layer.
    // Why: Returning native PostGIS GeoJSON aggregation avoids Jackson serialization overhead and Java-side map building.
    // Impact: Avoids multiple String copying for large sets, memory allocation for Map parsing and JSON rendering overhead.
    @SqlQuery("SELECT json_build_object(" +
            "'type', 'FeatureCollection'," +
            "'features', COALESCE(" +
            "    json_agg(" +
            "        json_build_object(" +
            "            'type', 'Feature'," +
            "            'geometry', ST_AsGeoJSON(geom)::json," +
            "            'properties', json_build_object(" +
            "                'id', id," +
            "                'name', name," +
            "                'description', description" +
            "            )" +
            "        )" +
            "    )," +
            "    '[]'::json" +
            ")" +
            ")::text FROM pois")
    String getPoisAsGeoJson();
}
