## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-31 - [Spatial Data JSON Serialization Optimization]
**Learning:** [In Dropwizard/JDBI applications dealing with spatial data, manually querying rows and parsing `ST_AsGeoJSON` outputs in Java using ObjectMapper, HashMaps, and ArrayLists causes immense memory pressure and GC overhead for large datasets. Additionally, returning a raw JSON string from a method annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string if it is not returned as bytes.]
**Action:** [Use PostGIS native JSON aggregation (`json_build_object`, `json_agg`) with `ST_AsGeoJSON(geom)::json` and `COALESCE` directly in the JDBI SQL query. Return the resulting string as a byte array (`.getBytes(StandardCharsets.UTF_8)`) in the JAX-RS Resource to bypass Jackson serialization entirely.]
