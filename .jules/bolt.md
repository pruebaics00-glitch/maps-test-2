## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation]
**Learning:** [In Dropwizard/JDBI3, loading many rows into Java memory to manually build GeoJSON features requires excessive Jackson parsing and generates massive garbage collection overhead. Returning raw JSON strings directly bypassing serialization is much faster.]
**Action:** [Use PostGIS native JSON aggregation (`json_build_object`, `json_agg`, and `ST_AsGeoJSON(geom)::json`) directly in JDBI SQL queries. Apply `COALESCE` with `::json` cast on `json_agg` to handle empty tables safely (`COALESCE(json_agg(...), '[]'::json)`).]
