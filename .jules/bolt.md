## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-24 - [PostGIS JSON Aggregation Serialization Bypass]
**Learning:** [When returning native PostGIS generated JSON (like a FeatureCollection) from a JAX-RS resource using Dropwizard, returning the raw JSON string directly with `Response.ok(jsonString).build()` causes Jackson to double-encode it. Returning it as a UTF-8 byte array bypasses this.]
**Action:** [Use `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()` when returning pre-rendered JSON strings from the database.]
