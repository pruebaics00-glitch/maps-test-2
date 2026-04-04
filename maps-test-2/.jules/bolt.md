## 2026-04-04 - PostGIS Native JSON Aggregation
**Learning:** Returning large spatial datasets by aggregating them into JSON in Java adds significant overhead due to object instantiation and Jackson serialization/deserialization.
**Action:** When handling spatial queries that are just passed directly to the client, offload the JSON construction to PostGIS using `json_build_object` and `json_agg` coupled with `ST_AsGeoJSON(geom)::json`. Wrap `json_agg` in `COALESCE(..., '[]'::json)` to correctly handle empty datasets.
