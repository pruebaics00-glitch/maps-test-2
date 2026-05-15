## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-15 - [PostGIS JSON Aggregation in Backend]
**Learning:** [In a Java Dropwizard/JDBI backend querying a PostGIS database, parsing `ST_AsGeoJSON` and constructing the FeatureCollection in Java using Jackson object trees causes significant memory/CPU overhead, especially since Jackson serializes string GeoJSON inside the geometry object resulting in double encoding if not careful.]
**Action:** [Delegate GeoJSON FeatureCollection generation entirely to PostGIS natively by composing `json_build_object` and `json_agg` in the SQL query, and returning the raw string (encoded as bytes to avoid Dropwizard Jackson serializing the string again) via the REST API.]
