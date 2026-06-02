## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-06-02 - [PostGIS Native JSON Aggregation for GeoJSON]
**Learning:** [Fetching numerous geographic records as Java objects, mapping them manually, and converting them to JSON causes significant JVM memory usage and overhead. Returning raw JSON strings in Dropwizard causes Jackson double-encoding if not returned as a byte array.]
**Action:** [Delegate spatial JSON construction entirely to the database using PostGIS functions `json_build_object` and `json_agg` with proper `COALESCE` handling. In Dropwizard, wrap the string in `.getBytes(StandardCharsets.UTF_8)` to avoid double-encoding.]
