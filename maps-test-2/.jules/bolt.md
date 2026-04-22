## 2024-04-22 - JAX-RS Jackson Double Encoding
**Learning:** Returning a pre-serialized JSON string from a JAX-RS resource marked with `@Produces(MediaType.APPLICATION_JSON)` will cause Jackson to double-encode the string as a JSON value, quoting it.
**Action:** When returning a raw JSON string from a JAX-RS endpoint, return it as a byte array (`string.getBytes(StandardCharsets.UTF_8)`) to bypass Jackson's string serializer.

## 2024-04-22 - PostGIS JSON Aggregation
**Learning:** Loading thousands of PostGIS geometries into Java objects just to serialize them to GeoJSON with Jackson incurs massive memory and CPU overhead. PostGIS can natively build complete GeoJSON FeatureCollections efficiently within the database.
**Action:** Use `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json` (with `COALESCE(..., '[]'::json)` for empty sets) directly in SQL queries to perform spatial JSON aggregation at the database level, avoiding Java application bottlenecks entirely.