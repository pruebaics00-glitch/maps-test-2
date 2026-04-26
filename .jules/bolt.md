## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-04-25 - [PostGIS JSON Aggregation for Backend Performance]
**Learning:** [Dropwizard/Jackson struggles with high memory overhead and CPU usage when fetching many rows with ST_AsGeoJSON, deserializing them into Java objects/maps, and then re-serializing them to JSON strings. Jackson double-encodes raw JSON strings returned directly.]
**Action:** [Offload GeoJSON construction directly to PostGIS using `json_build_object` and `json_agg`, explicitly casting `ST_AsGeoJSON(geom)::json` to prevent text-escaping. Return raw string as `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()` to bypass Jackson overhead.]
