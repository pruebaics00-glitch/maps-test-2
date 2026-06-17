## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2024-03-18 - [PostGIS Native JSON Aggregation]
**Learning:** [In Dropwizard/JDBI applications dealing with spatial data, pulling raw records into Java (`Poi` objects), parsing inner `ST_AsGeoJSON` strings with Jackson, and manually building maps creates significant memory and CPU overhead. Returning a raw JSON string from a resource annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string.]
**Action:** [Use PostGIS `json_build_object` and `json_agg` directly in the JDBI SQL query to construct the complete GeoJSON payload in the database. Ensure nested `ST_AsGeoJSON` calls are cast to `::json` to avoid text escaping. When returning the raw JSON string from the Dropwizard Resource, wrap it in `.getBytes(StandardCharsets.UTF_8)` to bypass Jackson serialization.]
