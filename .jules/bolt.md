## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-11 - [Backend Spatial Data JSON Aggregation]
**Learning:** [In Dropwizard/PostGIS applications, aggregating spatial data using Jackson object mappers to parse GeoJSON and construct `FeatureCollection` objects introduces unnecessary memory allocation and serialization overhead.]
**Action:** [Leverage PostGIS native JSON aggregation (`json_build_object` and `json_agg`) directly in JDBI queries to build the complete GeoJSON string. To avoid Jackson double-encoding the raw string in Jax-RS, return the output as a UTF-8 byte array (`Response.ok(json.getBytes(StandardCharsets.UTF_8)).build()`).]
