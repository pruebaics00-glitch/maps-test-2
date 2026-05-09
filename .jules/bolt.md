## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS JSON Aggregation over Jackson Serialization]
**Learning:** [In a Dropwizard/JDBI3 application handling PostGIS geometry, fetching large amounts of rows into Java `Map`/`List` objects and serializing them via Jackson consumes high memory and causes serialization overhead.]
**Action:** [Use PostGIS native `json_build_object` and `json_agg` in the SQL query to generate the JSON directly. Ensure the result is returned as a byte array (`getBytes(StandardCharsets.UTF_8)`) when using JAX-RS `Response.ok()` to prevent Jackson from double-encoding the raw string.]
