## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-24 - [PostGIS native JSON aggregation overhead elimination]
**Learning:** [In Dropwizard/JAX-RS backends handling spatial data, extracting geometries manually, and passing entities via Java lists to generate GeoJSON results in high Jackson serialization costs and memory overhead. Returning raw JSON strings directly from `Response.ok(jsonString).build()` can lead to double-encoding if Jackson handles it.]
**Action:** [Offload JSON/GeoJSON object generation directly to Postgres using `json_build_object` and `json_agg` (casting explicitely via `::json`), and then stream the result directly back to the client as a byte array (`getBytes(StandardCharsets.UTF_8)`) to bypass Jackson entirely.]
