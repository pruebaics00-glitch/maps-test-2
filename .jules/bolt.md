## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-07 - [Native PostGIS JSON Aggregation and Jackson Double-Encoding]
**Learning:** [Using PostGIS native JSON aggregation (`json_build_object`, `json_agg`) with `ST_AsGeoJSON(geom)::json` bypasses Java-side memory overhead and Jackson serialization. When returning this raw JSON string in Dropwizard/JAX-RS annotated with `@Produces(MediaType.APPLICATION_JSON)`, returning it directly causes Jackson to double-encode it. `COALESCE` must be applied directly on the `json_agg` inner call with `::json` cast before wrapping it in the outer `json_build_object`.]
**Action:** [Return the raw JSON string as a byte array (`Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`) to bypass Jackson double-encoding. Use PostGIS JSON aggregation natively instead of fetching rows and building JSON objects in Java.]
