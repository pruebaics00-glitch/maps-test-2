## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]
## 2024-04-21 - [PostGIS native JSON aggregation and Jackson Double-Encoding]
**Learning:** [Using PostGIS `json_agg` and `ST_AsGeoJSON` allows pushing the entire FeatureCollection construction to the database, preventing memory overhead from loading raw geometry strings into JVM objects. However, returning the pre-formatted JSON string directly in a Dropwizard JAX-RS endpoint annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string (adding quotes and escaping characters).
**Action:** [Return the native JSON string as a `byte[]` using `string.getBytes(StandardCharsets.UTF_8)` to bypass Jackson serialization and return the raw unescaped JSON text.]
