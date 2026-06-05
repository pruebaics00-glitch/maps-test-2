## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-24 - [PostGIS native JSON aggregation]
**Learning:** [Offloading GeoJSON serialization entirely to the database via PostGIS native JSON aggregation (`json_build_object`, `json_agg`, and `ST_AsGeoJSON`) is significantly more efficient than fetching individual geometries and attributes back to Java and manually serializing them with Jackson (`ObjectMapper`). Using `COALESCE(..., '[]'::json)` prevents null issues on empty results.]
**Action:** [When building endpoints that return spatial data, utilize database-level JSON serialization instead of instantiating numerous Java POJOs and running them through Jackson.]
