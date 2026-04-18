## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-04-18 - [Dropwizard/Jackson String Serialization Pitfall]
**Learning:** [In Dropwizard/JAX-RS, returning a raw JSON string using `Response.ok(jsonString).build()` from a resource annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string as a JSON primitive (adding extra quotes and escaping). This breaks API contracts when clients expect a JSON object.]
**Action:** [To return pre-serialized JSON strings (like those aggregated via PostGIS `json_build_object`) without double-encoding, bypass Jackson by returning raw bytes: `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`.]
