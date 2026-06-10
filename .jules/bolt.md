## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation for Spatial Data]
**Learning:** [In Dropwizard/JAX-RS architectures, serializing large spatial datasets (like POIs with GeoJSON) through Java objects and Jackson can cause excessive JVM heap allocation, GC pressure, and double-encoding issues. Building the `FeatureCollection` entirely in PostGIS using `json_build_object` and `json_agg` (casting `ST_AsGeoJSON(geom)::json` properly) returns a pre-formatted JSON string directly, avoiding Java-side processing overhead entirely.]
**Action:** [For read-heavy endpoints serving spatial data or large JSON structures, offload the JSON serialization/aggregation directly to PostgreSQL/PostGIS. Stream the resulting string directly back as a byte array (`Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`) to bypass Jackson double-encoding.]
