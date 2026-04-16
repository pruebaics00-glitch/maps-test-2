## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation vs Java Parsing]
**Learning:** [Building complex GeoJSON objects on the backend using Java POJOs, Jackson tree parsers, and HashMap iterations causes unnecessary memory allocation, object graph instantiation, and serialization overhead when dealing with spatial data.]
**Action:** [Offload GeoJSON aggregation directly to the database using PostGIS native functions like `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json`. Wrap the array in `COALESCE(..., '[]'::json)` to handle empty sets, and return the raw string directly from the JAX-RS resource.]
