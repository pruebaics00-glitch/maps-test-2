## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS JSON Aggregation & JAX-RS Byte Array Return]
**Learning:** Returning a raw JSON string directly via Response.ok(jsonString).build() in a class annotated with @Produces(MediaType.APPLICATION_JSON) causes Jackson to double-encode the string. Additionally, PostGIS json_agg allows building complete GeoJSON feature collections natively in the database, avoiding Java map/object creation and Jackson serialization overhead.
**Action:** When offloading JSON building to PostGIS, return the resulting string as a byte array (Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()) to bypass Jackson serialization and prevent extraneous quotes. Also use COALESCE(json_agg(...), '[]'::json) to handle empty tables correctly.
