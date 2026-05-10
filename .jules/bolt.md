## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-10 - [PostGIS JSON Aggregation & Jersey Jackson Double Encoding]
**Learning:** [When dealing with spatial data in Dropwizard/JAX-RS, returning native PostGIS JSON aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON`) directly from JDBI SQL queries as a text string is much more performant than Java-side mapping with Jackson. However, returning a raw JSON string directly via `Response.ok(jsonString).build()` in a class annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string. This results in extraneous quotes around the JSON response.]
**Action:** [To bypass Jackson serialization and prevent double-encoding of raw JSON strings, return the string as a byte array: `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`.]
