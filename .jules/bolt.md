## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-05-21 - [Backend Java Object Mapping and Jackson Serialization Overhead]
**Learning:** [In Dropwizard/JDBI applications, mapping thousands of spatial records into Java objects and then using Jackson to serialize them into GeoJSON causes significant memory overhead and high CPU usage. Jackson serialization overhead and double-encoding issues can be sidestepped entirely for responses that are just raw JSON strings by returning a byte array: `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`.]
**Action:** [Use native PostGIS json aggregation (`json_build_object`, `json_agg`, `ST_AsGeoJSON`) to construct GeoJSON strings directly in PostgreSQL, and bypass Jackson entirely by returning the resulting string as a byte array in JAX-RS resources.]
