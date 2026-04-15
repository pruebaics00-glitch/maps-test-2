## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-24 - [PostGIS Native JSON Aggregation]
**Learning:** [Constructing GeoJSON in Java with Jackson and Map structures creates memory overhead and redundant serialization. PostGIS can natively build the entire FeatureCollection faster and with less memory overhead using json_build_object, json_agg, and ST_AsGeoJSON.]
**Action:** [Use native PostGIS JSON aggregation directly in the SQL query and return the raw JSON string from the JAX-RS resource.]
