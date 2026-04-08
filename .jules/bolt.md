## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-04-08 - [PostGIS Native JSON Aggregation for Memory Overhead Reduction]
**Learning:** [In Dropwizard/JDBI backends generating GeoJSON, fetching raw rows to construct large object hierarchies for Jackson serialization creates enormous Java memory overhead (instantiating thousands of objects/maps/lists) and CPU bottlenecks.]
**Action:** [Use PostGIS native JSON functions (`json_build_object`, `json_agg`, `ST_AsGeoJSON`) directly in the database query. Wrap the `json_agg` in `COALESCE` to handle empty tables gracefully. Return the resulting string directly through the API, bypassing Java object creation entirely.]
