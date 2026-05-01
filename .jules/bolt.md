## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-01 - [PostGIS Native JSON Aggregation over Java Serialization]
**Learning:** [Processing spatial JSON (like GeoJSON) in Java by iterating through rows, parsing ST_AsGeoJSON strings with Jackson `ObjectMapper`, and building massive `HashMap` structures to represent FeatureCollections introduces severe memory allocation and serialization overhead.]
**Action:** [Use native PostGIS JSON aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON(geom)::json`) directly in JDBI `@SqlQuery` to return a fully constructed JSON string. Return this string from JAX-RS as a byte array (`getBytes(StandardCharsets.UTF_8)`) to bypass Jackson double-encoding.]
