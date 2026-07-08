## 2025-02-12 - Prevent JAX-RS Double-Encoding Raw JSON
**Learning:** In Dropwizard/JAX-RS, returning a raw JSON string using `Response.ok(jsonString).build()` from a method annotated with `@Produces(MediaType.APPLICATION_JSON)` causes Jackson to incorrectly double-encode the output string.
**Action:** When bypassing standard POJO serialization and returning pre-generated JSON (e.g., from PostGIS queries), always convert the string to raw bytes (`Response.ok(jsonString.getBytes(StandardCharsets.UTF_8)).build()`) to bypass the Jackson serialization layer.

## 2025-02-12 - Maintain Repository Hygiene After Java Builds
**Learning:** Running `mvn package` or `mvn test` during validation generates `target/` directories and modify `dependency-reduced-pom.xml` files. If you run a build, these temporary build artifacts will show up as modified untracked/tracked files in `git status`.
**Action:** Always run `git status` right before creating a commit or PR. If build artifacts appear, use `git restore --staged` to unstage them, `git checkout HEAD --` to restore tracked files to their original state, and `rm` to remove untracked generated files.
