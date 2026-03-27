## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-18 - [PostGIS Native GeoJSON Construction]
**Learning:** [Constructing GeoJSON FeatureCollections in Java by iterating through database rows, building intermediate HashMaps, and parsing ST_AsGeoJSON strings with Jackson ObjectMapper is a massive performance bottleneck causing excessive heap allocations and CPU usage. PostGIS can construct the entire JSON payload natively, but you must wrap json_agg with COALESCE to prevent null results when the table is empty.]
**Action:** [Always use PostGIS native JSON functions (json_build_object, json_agg) directly in JDBI @SqlQuery to construct and return a single, ready-to-serve JSON string from the database, eliminating backend serialization loops.]
