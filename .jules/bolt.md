## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-07-05 - [PostGIS Native JSON Aggregation]
**Learning:** [In Dropwizard/JDBI3, retrieving spatial data as Java POJOs and manually constructing GeoJSON in the application layer causes significant memory overhead and Jackson serialization cost. PostGIS's `json_build_object` and `json_agg` can natively assemble GeoJSON directly in the database.]
**Action:** [Use `json_build_object` and `json_agg` in JDBI SQL queries to return JSON strings directly, bypassing Java-side memory overhead and Jackson serialization. Remember to return the string as a byte array (`Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`) to avoid double-encoding by Jackson.]
