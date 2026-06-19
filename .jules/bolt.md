## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation]
**Learning:** [Using PostGIS `json_build_object` and `json_agg` directly in JDBI queries to build and return JSON strings significantly reduces Java memory overhead and eliminates Jackson serialization bottlenecks by pushing the work to the database.]
**Action:** [Use database-native JSON aggregation with JDBI `@SqlQuery` returning `String` and returning as `.getBytes(StandardCharsets.UTF_8)` when serving large spatial datasets instead of looping in Java and allocating temporary Map/POJO objects.]
