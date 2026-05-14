## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-14 - [PostGIS Native JSON Aggregation]
**Learning:** [PostGIS native JSON aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON`) can significantly reduce Java-side memory overhead compared to fetching entities and mapping them to a feature collection in Java. When returning the raw JSON string directly in Dropwizard, bypass Jackson serialization by returning it as a byte array (`Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`) to avoid extraneous quotes and double encoding.]
**Action:** [Prefer PostGIS native JSON aggregation for spatial data handling. Remember to use `COALESCE` with a `::json` cast on the `json_agg` inner call to avoid issues on empty tables, and return strings as byte arrays to bypass Jackson.]
