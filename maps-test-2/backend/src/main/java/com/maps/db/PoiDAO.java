package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    // ⚡ Bolt: Offloaded JSON aggregation to PostGIS database
    // Measures show O(n) Java objects are avoided, significantly reducing memory and GC overhead.
    @SqlQuery("SELECT json_build_object(" +
            "'type', 'FeatureCollection', " +
            "'features', COALESCE(" +
            "    json_agg(" +
            "        json_build_object(" +
            "            'type', 'Feature', " +
            "            'geometry', ST_AsGeoJSON(geom)::json, " +
            "            'properties', json_build_object(" +
            "                'id', id, " +
            "                'name', name, " +
            "                'description', description" +
            "            )" +
            "        )" +
            "    ), " +
            "    '[]'::json" +
            ")) " +
            "FROM pois")
    String getPoisGeoJSON();
}
