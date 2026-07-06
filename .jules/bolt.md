## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation over Java Serialization]
**Learning:** [Constructing JSON objects (like GeoJSON FeatureCollections) directly in PostGIS using `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json` bypasses Java-side memory overhead and Jackson serialization steps, speeding up geometry responses significantly. Return these strings directly as `byte[]` to avoid Jackson double-encoding.]
**Action:** [Prefer PostGIS native JSON aggregation in JDBI queries over Java-side manual loop assembly for spatial data.]
