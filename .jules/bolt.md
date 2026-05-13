## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-13 - [PostGIS JSON Aggregation & Jackson Double-Encoding Bypass]
**Learning:** [In Dropwizard/JAX-RS with PostGIS, building GeoJSON objects Java-side introduces memory overhead and Jackson double-encoding when returning a JSON string. We can use native PostGIS functions `json_build_object` and `json_agg` directly in the query. Returning raw JSON strings requires converting them to `byte[]` to prevent Jackson from wrapping them in extra quotes.]
**Action:** [Use PostGIS `json_build_object` and `json_agg` to build JSON structures in the database, ensuring `ST_AsGeoJSON(geom)::json` has the cast. In JAX-RS `Response.ok()`, return the raw JSON string as `geoJsonString.getBytes(StandardCharsets.UTF_8)` to bypass Jackson.]
