## 2024-03-18 - [MapLibre Redundant Render on Data Fetch]
**Learning:** [React `useEffect` combining theme change (which requires a MapLibre `setStyle`) and data fetch triggers a full style recalculation on every data load. MapLibre `setStyle` clears layers.]
**Action:** [Decouple the theme configuration from data fetching by using a Ref for dynamic data within the configuration block and handling data synchronization in its own decoupled `useEffect`.]

## 2026-03-19 - Unnecessary String to JSON Node parsing in Jackson
**Learning:** Found an architectural anti-pattern where a raw JSON string from a database (like ST_AsGeoJSON) was being parsed into a Jackson `JsonNode` (using `mapper.readTree()`) only to be immediately embedded in an object that gets re-serialized to JSON. This burns CPU parsing and re-serializing unnecessarily.
**Action:** Use Jackson's `new com.fasterxml.jackson.databind.util.RawValue(jsonString)` to inject pre-formatted JSON directly into objects without incurring the cost of parsing it. This avoids expensive String -> JSON tree -> String conversions.
