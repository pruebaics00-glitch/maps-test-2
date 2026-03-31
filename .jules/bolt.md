## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-18 - [PostGIS Native GeoJSON Serialization]
**Learning:** [Java object allocations (like HashMap/ArrayList per row) and Jackson JsonNode tree generation create a massive N+1 memory bottleneck when serializing large spatial datasets (POIs) into a GeoJSON FeatureCollection.]
**Action:** [Use Postgres's `json_build_object` and `json_agg` along with PostGIS's `ST_AsGeoJSON` directly in the `@SqlQuery` to pre-aggregate the entire collection on the database, allowing the backend to return an O(1) string pass-through and bypass Jackson completely.]
