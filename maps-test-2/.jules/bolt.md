## 2024-07-04 - PostGIS Native JSON Aggregation
**Learning:** Returning a raw JSON string directly using `Response.ok(jsonString).build()` in a class annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to double-encode the string.
**Action:** To bypass Jackson serialization and prevent extraneous quotes, return the string as a byte array: `Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`.

## 2024-07-04 - PostGIS json_agg with COALESCE
**Learning:** When using PostGIS `json_agg` inside `json_build_object` to build GeoJSON feature collections, `json_agg` evaluates to NULL on empty tables. An outer COALESCE fails because `json_build_object` wraps it into `{'features': null}`.
**Action:** Always apply `COALESCE` with a `::json` cast directly on the `json_agg` inner call (e.g., `COALESCE(json_agg(...), '[]'::json)`) before wrapping it in the outer `json_build_object`.
