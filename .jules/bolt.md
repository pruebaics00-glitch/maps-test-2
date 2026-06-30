## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-20 - [PostGIS Native JSON Aggregation for Spatial Data]
**Learning:** [In this Dropwizard/PostGIS architecture, constructing GeoJSON features in Java (O(n) object creation + Jackson serialization) creates significant overhead for large datasets. PostGIS can natively aggregate features into a GeoJSON FeatureCollection using `json_build_object`, `json_agg`, and `ST_AsGeoJSON`.]
**Action:** [Use PostGIS native JSON aggregation directly in JDBI SQL queries and return the resulting raw JSON string as a byte array to bypass Jackson double-encoding.]
