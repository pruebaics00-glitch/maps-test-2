## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-24 - Offloading GeoJSON Aggregation to PostGIS
**Learning:** Returning large spatial datasets by querying PostGIS for geometries, converting them to Java objects via JDBI, parsing them into Jackson Trees, and then re-serializing them to JSON strings creates massive overhead (CPU usage, object churn, and memory allocation).
**Action:** Instead of piecing together GeoJSON FeatureCollections in application code, use PostGIS's `json_build_object` and `json_agg` functions to generate the entire JSON string in the database layer. Then, stream the pre-built JSON string directly to the client via a JAX-RS `StreamingOutput`, bypassing Jackson serialization entirely. This provides immense performance gains for large spatial queries.
