## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation]
**Learning:** [In Dropwizard/JDBI applications, querying PostGIS spatial data and mapping it to Java objects (like `Poi.java`) just to manually rebuild a GeoJSON `FeatureCollection` using Jackson creates unnecessary memory overhead, many object allocations, and slow JSON serialization.]
**Action:** [Offload GeoJSON aggregation directly to the database using PostGIS native functions (`json_build_object`, `json_agg`, `ST_AsGeoJSON`). Cast the final result to `::text` and return it directly from the endpoint as a `byte[]` to bypass Jackson's double-encoding.]
