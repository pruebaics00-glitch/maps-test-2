## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS JSON Aggregation for Performance]
**Learning:** [In Dropwizard/JDBI3, aggregating large datasets into GeoJSON on the Java side incurs massive memory allocation and Jackson parsing overhead. Using PostGIS native JSON aggregation (`json_build_object`, `json_agg`) pushes the work to the DB and returns a single pre-aggregated JSON string.]
**Action:** [Use native DB JSON aggregation where possible, ensuring proper casting (e.g., `ST_AsGeoJSON(geom)::json`) and `COALESCE` for empty sets to return valid JSON, and return raw byte arrays in Dropwizard to bypass double-encoding.]
