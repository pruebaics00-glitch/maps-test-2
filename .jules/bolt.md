## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native GeoJSON Aggregation]
**Learning:** [Fetching thousands of rows into Java POJOs, converting them into HashMaps, and parsing ST_AsGeoJSON strings via `ObjectMapper.readTree` creates significant memory overhead and high CPU usage in the backend.]
**Action:** [Use PostGIS native JSON aggregation (`json_build_object` and `json_agg`) directly in JDBI queries to build the complete `FeatureCollection` in PostgreSQL. Ensure `COALESCE(..., '[]'::json)` is used for empty tables. Return the raw string as a byte array to avoid Jackson double-encoding.]
