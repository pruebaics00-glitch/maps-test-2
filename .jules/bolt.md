## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-04-27 - [Backend Spatial Aggregation Memory Bottleneck]
**Learning:** [Dropwizard applications handling large GIS datasets face severe memory pressure and GC pauses when fetching rows into Java objects and converting via Jackson. Using PostGIS `json_agg` and `ST_AsGeoJSON` is orders of magnitude faster. However, returning a JSON string in Dropwizard double-encodes it. It must be cast to `json` in PostgreSQL, and returned as a raw byte array `getBytes(StandardCharsets.UTF_8)` from JAX-RS.]
**Action:** [Prefer PostGIS native JSON aggregation over Java-side processing for GeoJSON endpoints, and always return pre-serialized JSON as a byte array in Dropwizard.]
