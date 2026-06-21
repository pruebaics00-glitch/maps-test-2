## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS JSON Aggregation for Dropwizard Response]
**Learning:** [Jackson double-encodes JSON string representations inside entities when using `@Produces(MediaType.APPLICATION_JSON)`. Additionally, iterating and mapping `Poi` items in Java to reconstruct a GeoJSON is inefficient when PostGIS has native JSON aggregation functions.]
**Action:** [Use `json_agg` and `json_build_object` combined with `ST_AsGeoJSON(geom)::json` inside JDBI SQL queries. Have JDBI return a `String` and let the JAX-RS resource return the raw JSON as `byte[]` (`Response.ok(geoJson.getBytes(StandardCharsets.UTF_8)).build()`) to bypass Jackson double-encoding.]
