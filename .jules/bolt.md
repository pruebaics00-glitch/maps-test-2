## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2026-04-06 - [PostGIS Native JSON Aggregation vs Java Jackson Serialization]
**Learning:** [Fetching geometries to Java and using Jackson `ObjectMapper.readTree` to iterate and build GeoJSON `FeatureCollection` structures incurs massive GC overhead and blocks CPU for large datasets.]
**Action:** [Offload the JSON creation entirely to PostGIS utilizing `json_build_object` combined with `json_agg` and explicitly casting `ST_AsGeoJSON(geom)::json`. The backend should only pass the resulting raw JSON String through the JAX-RS Response.]
