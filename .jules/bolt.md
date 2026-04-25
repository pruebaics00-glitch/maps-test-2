## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS JSON Aggregation and Jackson Double Encoding]
**Learning:** [Returning a raw JSON string directly using `Response.ok(jsonString).build()` in Dropwizard/JAX-RS annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string. Additionally, Java-side GeoJSON object assembly creates unnecessary memory overhead when database engines like PostgreSQL/PostGIS can natively format output.]
**Action:** [Offload JSON/GeoJSON structure assembly directly to PostGIS using `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json`. Bypass Jackson string double-encoding by returning the raw string as a byte array: `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`.]
