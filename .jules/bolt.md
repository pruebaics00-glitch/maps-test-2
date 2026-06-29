## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS JSON Aggregation for Performance]
**Learning:** [Building complex JSON objects (like GeoJSON FeatureCollections) natively in PostGIS via `json_build_object` and `json_agg` is vastly more performant than returning geometry text, mapping to Java objects, and using Jackson for serialization. It saves memory and processing overhead.]
**Action:** [Use `ST_AsGeoJSON(geom)::json` nested within `json_agg` and return the complete payload as a raw String/byte array directly from the database query to the REST response, bypassing Jackson entirely.]
