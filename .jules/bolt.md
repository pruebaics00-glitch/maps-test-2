## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation]
**Learning:** [Java-side object mapping and iteration for GeoJSON conversion creates massive memory overhead and serialization costs. Jackson's default serialization doubles the overhead.]
**Action:** [Offload GeoJSON aggregation directly to PostGIS using `json_build_object` and `json_agg`, returning the raw JSON string directly to the Jersey response, avoiding any Java object heap allocations for large spatial datasets.]
