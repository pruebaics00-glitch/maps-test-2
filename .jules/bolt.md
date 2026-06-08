## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS JSON Aggregation & Jackson Serialization Bypass]
**Learning:** [In Dropwizard/JAX-RS, constructing JSON from database rows in Java creates significant memory overhead and garbage collection pauses. Furthermore, returning a raw JSON string directly using `Response.ok(jsonString).build()` causes Jackson to double-encode the string. This can be mitigated by performing PostGIS native JSON aggregation (`json_build_object`, `json_agg`, and `ST_AsGeoJSON`) directly in JDBI SQL queries, and bypassing Jackson serialization by returning the string as a byte array (`StandardCharsets.UTF_8`).]
**Action:** [Prefer PostGIS native JSON aggregation for spatial data and bypass Jackson by returning byte arrays for pre-constructed JSON strings to avoid double-encoding overhead.]
