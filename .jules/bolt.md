## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation over Java-side Object Instantiation]
**Learning:** [In Dropwizard/JAX-RS, converting rows to Java objects and building a deep JSON tree using ObjectMapper creates unnecessary garbage, uses excess memory and cpu time. The best practice for spatial JSON representation is doing it right in the SQL query using PostGIS json_build_object and json_agg.]
**Action:** [Prefer PostGIS native JSON aggregation (`json_build_object`, `json_agg`, and `ST_AsGeoJSON::json`) directly in JDBI SQL queries. Return as byte array (`getBytes(StandardCharsets.UTF_8)`) to bypass Jackson double-encoding.]
