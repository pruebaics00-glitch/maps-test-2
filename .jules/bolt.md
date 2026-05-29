## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-18 - [PostGIS JSON Aggregation and Jackson Double-Encoding]
**Learning:** [Building GeoJSON FeatureCollections manually in Java via loop causes high memory overhead and serialization cost. Furthermore, returning a raw JSON string from a JAX-RS endpoint annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode it.]
**Action:** [Use PostGIS `json_build_object` and `json_agg` with `ST_AsGeoJSON(geom)::json` to build the GeoJSON in the DB. Return the result as a byte array (`jsonString.getBytes(StandardCharsets.UTF_8)`) to bypass Jackson serialization and prevent extraneous quotes.]
