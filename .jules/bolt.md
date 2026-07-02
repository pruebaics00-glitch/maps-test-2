## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-07-02 - [Backend JSON Aggregation Optimization]
**Learning:** [Jackson object serialization on large datasets can be a backend performance bottleneck. It's often more efficient to offload JSON generation to PostGIS native JSON functions (`json_build_object`, `json_agg`) and return the resulting pre-computed JSON string directly as a byte array to bypass Jackson entirely.]
**Action:** [Use `json_build_object` and `json_agg` in SQL queries to build GeoJSON features. Use `COALESCE` with a `::json` cast directly on `json_agg` to handle empty sets before passing it to `json_build_object`. Finally, return the SQL JSON result string as a byte array via `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()` in JAX-RS endpoints.]
