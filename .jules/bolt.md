## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-24 - PostGIS Native GeoJSON Construction Edge Case
**Learning:** When using `json_agg` in PostGIS to construct a GeoJSON FeatureCollection array directly in the database query, an empty table will cause `json_agg` to evaluate to `null`. This bypasses simple `if (json == null)` null-checks in Java if it's nested inside `json_build_object`, leading to invalid GeoJSON payloads like `{"type": "FeatureCollection", "features": null}` being returned to the frontend, which breaks map renderers expecting an array `[]`.
**Action:** Always wrap `json_agg` with `COALESCE(..., '[]'::json)` when constructing JSON arrays in PostGIS queries to ensure valid empty states.
