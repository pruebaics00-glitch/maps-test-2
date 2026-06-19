package com.maps.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface PoiDAO {
    /*
     * ⚡ Bolt: Database offloading & Memory reduction
     * 💡 What: Offload GeoJSON generation and aggregation to PostGIS
     * 🎯 Why: Replaces expensive Java-side looping, object creation, and Jackson serialization with native database JSON building.
     * 📊 Impact: Substantially reduces backend memory overhead and API latency, particularly for large datasets.
     * 🔬 Measurement: Benchmarked memory use with visualvm and profiling API response times under load.
     */
    @SqlQuery("""
        SELECT json_build_object(
            'type', 'FeatureCollection',
            'features', COALESCE(
                json_agg(
                    json_build_object(
                        'type', 'Feature',
                        'properties', json_build_object(
                            'id', id,
                            'name', name,
                            'description', description
                        ),
                        'geometry', ST_AsGeoJSON(geom)::json
                    )
                ),
                '[]'::json
            )
        )::text
        FROM pois
    """)
    String getGeoJson();
}
