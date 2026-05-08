## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-08 - [Dropwizard/Jackson Double Encoding]
**Learning:** [In Dropwizard/JAX-RS, returning a raw JSON string directly using `Response.ok(jsonString).build()` in a class annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string.]
**Action:** [To bypass Jackson serialization and prevent extraneous quotes, return the string as a byte array: `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`.]
