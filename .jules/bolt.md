## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-24 - [PostGIS JSON Aggregation and Jackson Double-Encoding]
**Learning:** [Constructing large GeoJSON responses element-by-element in Java via Jackson `ObjectMapper` is memory intensive. By pushing aggregation to PostgreSQL using `json_build_object` and `json_agg`, we eliminate Java-side object creation. Furthermore, returning the resulting JSON string directly via a JAX-RS endpoint annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode it. To bypass this, the raw JSON string must be returned as a `byte[]`.]
**Action:** [Prefer PostGIS native JSON aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON(geom)::json`) for spatial data. When returning pre-serialized JSON strings in Dropwizard/JAX-RS, always return them as `string.getBytes(StandardCharsets.UTF_8)` to avoid double-encoding.]
