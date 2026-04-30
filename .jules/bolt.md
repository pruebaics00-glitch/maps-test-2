## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-11-20 - [PostGIS JSON Aggregation in Dropwizard]
**Learning:** [Offloading spatial JSON generation (like GeoJSON FeatureCollections) to PostGIS via native JSON aggregation (e.g. json_build_object, json_agg, ST_AsGeoJSON) prevents Java-side memory and Jackson serialization overhead. However, when returning this raw JSON string in Dropwizard/JAX-RS annotated with @Produces(MediaType.APPLICATION_JSON), Jackson will double-encode it if returned as a String. It must be converted to a byte array (e.g. getBytes(StandardCharsets.UTF_8)) to bypass this serialization.]
**Action:** [Use PostGIS for complex JSON building natively in the database and always return the resulting String as a byte array in Dropwizard endpoints to avoid double-encoding.]
