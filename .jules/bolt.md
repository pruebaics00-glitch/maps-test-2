## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-04-11 - [PostGIS Native JSON Aggregation for FeatureCollections]
**Learning:** [Generating GeoJSON FeatureCollections in Java using Jackson and Map/List allocations creates massive memory and CPU overhead. PostGIS can natively build the entire JSON response using `json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json` (must cast to JSON to avoid escaping). Dropwizard/JAX-RS can return this raw string directly via `Response.ok()` if annotated with `@Produces(MediaType.APPLICATION_JSON)`.]
**Action:** [Always prefer native database JSON aggregation for spatial data to avoid heavy backend serialization overhead, wrapping the `json_agg` in `COALESCE(..., '[]'::json)` to handle empty sets.]
