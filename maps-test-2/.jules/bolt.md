## 2026-06-27 - Offload GeoJSON Aggregation to PostGIS
**Learning:** Native DB JSON aggregation (`json_build_object`, `json_agg`) combined with `ST_AsGeoJSON` avoids moving large result sets into Java memory. In JAX-RS, returning raw JSON bytes bypassing Jackson serialization prevents double-encoding.
**Action:** Use PostGIS aggregation for spatial data endpoints and return `jsonString.getBytes(StandardCharsets.UTF_8)` to maximize backend performance.
