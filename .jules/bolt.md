## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-06-12 - [PostGIS JSON Aggregation & Jackson Serialization Bypass]
**Learning:** [Dropwizard/JAX-RS Jackson double-encodes raw JSON strings when returning `Response.ok(jsonString).build()`. Also, PostGIS native JSON aggregation is faster and avoids Java-side memory overhead compared to fetching POJOs and manually building JSON via Jackson.]
**Action:** [Offload JSON aggregation to PostgreSQL/PostGIS using `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json`. Return the raw string as a byte array (`geoJson.getBytes(StandardCharsets.UTF_8)`) to bypass Jackson double-encoding.]
