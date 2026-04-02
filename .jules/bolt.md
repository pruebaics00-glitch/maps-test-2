## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-24 - [PostGIS Native JSON Aggregation]
**Learning:** [Constructing large GeoJSON feature collections in Java causes high memory overhead and CPU usage due to creating many `HashMap` objects and serializing/deserializing geometries with Jackson. Doing this directly via JDBI results in poor performance.]
**Action:** [Offload spatial JSON generation to PostGIS. Combine `json_build_object`, `json_agg` wrapped in `COALESCE(..., '[]'::json)`, and `ST_AsGeoJSON(geom)::json` natively in the SQL query, and return the result directly via `Response.ok(jsonString).build()`.]
