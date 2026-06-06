## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-06-06 - [PostGIS Native JSON Aggregation for Maps]
**Learning:** Returning massive result sets of geometries from PostgreSQL to Java merely to build JSON feature collections causes huge memory overhead and unnecessary Jackson serialization work.
**Action:** Use PostGIS native functions (`json_build_object`, `json_agg`, and `ST_AsGeoJSON`) to aggregate data into a single GeoJSON string directly in the database. Ensure `ST_AsGeoJSON(geom)` is cast to JSON (`::json`) so it doesn't serialize as an escaped string, use `COALESCE` on `json_agg` for empty result sets, and return the final string as raw bytes from Dropwizard to bypass Jackson double-encoding.
