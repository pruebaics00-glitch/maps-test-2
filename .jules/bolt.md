## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-04 - [Backend GeoJSON Generation Optimization]
**Learning:** [Constructing GeoJSON objects in Java using Jackson (`ObjectMapper`, `Map`, `List`) creates significant overhead and temporary objects. Additionally, returning a raw JSON string directly using `Response.ok(jsonString).build()` in Dropwizard/JAX-RS when annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string.]
**Action:** [Shift the GeoJSON construction entirely to PostGIS using `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json`. Return the native JSON string directly from the database and serve it from the API by returning it as a byte array (`Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`) to bypass Jackson double-serialization overhead.]
