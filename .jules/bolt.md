## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-06-04 - [Backend GeoJSON Aggregation]
**Learning:** [In Dropwizard/JAX-RS with PostGIS, building GeoJSON in Java loop is extremely slow and memory intensive, plus it results in unnecessary Jackson serialization. Using PostGIS native JSON functions like `json_build_object` and `json_agg` directly inside SQL eliminates this overhead entirely. But note: returning the raw JSON string directly via `Response.ok(jsonStr)` triggers Jackson double-encoding. Must bypass Jackson serialization using `Response.ok(jsonStr.getBytes(StandardCharsets.UTF_8)).build()`.]
**Action:** [Use PostGIS `json_build_object` and `json_agg` to build and aggregate GeoJSON objects directly on the database side for significant performance gains when rendering spatial features on the backend, and always return raw strings as byte arrays to prevent double-encoding.]
