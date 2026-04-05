## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2026-04-05 - [PostGIS Native JSON Aggregation vs Jackson ObjectMapper]
**Learning:** [Using Java's Jackson ObjectMapper to map database rows into objects, only to serialize them back into a giant GeoJSON string, creates significant memory overhead and GC pressure. PostGIS can construct the GeoJSON directly using `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json`.]
**Action:** [Prefer PostGIS native JSON aggregation directly in JDBI SQL queries for spatial data to return raw JSON strings, completely bypassing Java-side memory overhead and Jackson serialization.]
