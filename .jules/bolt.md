## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-04-12 - [PostGIS JSON Aggregation for FeatureCollections]
**Learning:** [Constructing large GeoJSON FeatureCollections in Java using Jackson and manual iteration introduces significant memory overhead and serialization cost. PostGIS native JSON aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON`) in JDBI is much more performant.]
**Action:** [Use PostGIS JSON aggregation directly in database queries and return the resulting raw JSON string directly from the JAX-RS endpoint to bypass Java-side memory and serialization overhead.]
