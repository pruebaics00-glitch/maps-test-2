## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS native JSON aggregation overhead reduction]
**Learning:** [Using PostGIS native JSON aggregation (like `json_build_object`, `json_agg`, and `ST_AsGeoJSON`) directly in JDBI SQL queries is far more efficient than building large JSON strings in Java using Jackson and Object models. This avoids heavy memory overhead for thousands of features.]
**Action:** [Prefer aggregating features into `FeatureCollection` directly inside PostgreSQL returning a JSON string for GeoJSON endpoints, and ensure to return it as a byte array to bypass Jackson serialization.]
