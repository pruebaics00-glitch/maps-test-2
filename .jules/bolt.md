## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-24 - [PostGIS Native JSON Aggregation for GeoJSON]
**Learning:** [Using PostGIS native JSON aggregation (`json_build_object`, `json_agg`, and `ST_AsGeoJSON`) directly in JDBI SQL queries allows returning pre-built JSON strings, completely bypassing Java-side memory overhead and Jackson serialization costs when building GeoJSON FeatureCollections.]
**Action:** [Prefer native database JSON aggregation for spatial data formatting to minimize backend memory usage and CPU cycles, and return the result as a byte array to bypass Jackson double-encoding.]
