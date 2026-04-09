## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]
## 2024-04-09 - Offload Spatial JSON Aggregation to PostGIS
**Learning:** Returning large spatial datasets via Dropwizard by mapping `ST_AsGeoJSON` into Java domain objects and manually re-serializing them to `FeatureCollection` via Jackson creates significant memory overhead and loop bottlenecks.
**Action:** Always leverage PostGIS native JSON functions (`json_build_object`, `json_agg`, `ST_AsGeoJSON(geom)::json`) directly in SQL to compute and return the complete GeoJSON string. Use `Response.ok(jsonString).build()` to directly serve the string without Jackson intervention. Ensure `COALESCE` is used around `json_agg` to prevent `null` returns on empty tables.
