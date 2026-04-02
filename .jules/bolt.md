## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation vs Jackson Serialization]
**Learning:** [In Dropwizard/JAX-RS architectures serving geospatial data, loading entity properties into Java objects to map to a GeoJSON structure using Jackson serialization causes significant memory overhead and N+1 object instantiations. ]
**Action:** [Use native PostGIS JSON functions (`json_build_object`, `json_agg`, `ST_AsGeoJSON`) to aggregate the GeoJSON FeatureCollection directly in the database. Ensure the response is returned as a raw JSON string using `@Produces(MediaType.APPLICATION_JSON)`.]
