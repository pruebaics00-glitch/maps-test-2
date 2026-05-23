## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-23 - [PostGIS Native JSON Aggregation & Jackson String Encoding]
**Learning:** [Using PostGIS native JSON aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON`) directly in queries eliminates massive Java-side memory overhead when constructing large GeoJSON objects. However, returning the resulting raw JSON string directly using `Response.ok(jsonString).build()` in a class annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string (adding extraneous quotes). To bypass this serialization, you must return the string as a byte array (`jsonString.getBytes(StandardCharsets.UTF_8)`).]
**Action:** [When migrating data transformation to the database to improve performance, always ensure the raw output format doesn't conflict with intermediate serialization layers (like Jackson). Use byte array returns when explicitly bypassing these layers in JAX-RS.]
