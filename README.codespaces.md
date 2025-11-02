# Cotizador Hipotecario — Demo End‑to‑End en GitHub Codespaces

Este paquete agrega a tu repositorio los archivos necesarios para correr **en el navegador**, sin instalar nada:
- `.devcontainer/devcontainer.json` → prepara el entorno y lanza Docker Compose automáticamente.
- `Dockerfile` → build multi‑stage para empaquetar tu app Java + Spring Boot.
- `docker-compose.yml` → levanta `api` (tu app) y `db` (Postgres opcional).
- `.github/workflows/ci.yml` → CI con Maven.
- `requests.http` → pruebas rápidas desde VS Code (REST Client).

## Pasos (una vez que mezcles estos archivos en tu repo)
1. Sube tu repo a GitHub (si aún no lo hiciste).
2. Entra a **Code → CodeSpaces → Create codespace on main**.
3. Espera a que termine la preparación del contenedor. El `postCreateCommand` ejecutará:
   ```bash
   docker compose up -d --build
   ```
   y la URL de `http://localhost:8080` (túnel público) se abrirá sola.
4. Probar el endpoint:
   - Con el archivo `requests.http` (abre y haz clic en **Send Request**).
   - O con curl desde el terminal del Codespace:
     ```bash
     curl -s "http://localhost:8080/cotizaciones?monto=250000&plazoMeses=240&tipoCliente=PREMIUM" | jq .
     ```

> **Nota**: Tu proyecto actual usa un **adaptador en memoria** para persistencia. Así se ve perfecto el flujo hexagonal end‑to‑end (HTTP → caso de uso → dominio → repositorio/adaptadores).
> Si luego agregas un adaptador Postgres, el `docker-compose.yml` ya trae `db` listo.

## Qué mostrar en clase (Guion sugerido)
1. **Capas hexagonales**: `dominio` (entidades/puertos), `aplicacion` (casos de uso), `infraestructura` (adaptadores), `web` (controlador).
2. Llamada HTTP → **controlador** → **caso de uso** `CalcularCotizacionUseCase` → puertos `TasasMercadoPort` y `RepositorioCotizacionPort`.
3. Cambiar un adaptador: usar `RepositorioCotizacionEnMemoria` vs. (futuro) `RepositorioCotizacionPostgres` sin tocar dominio ni aplicación.
4. Ejecutar la prueba E2E con `requests.http` o `curl` y explicar idempotencia, validaciones y errores.

## Variables y perfiles
- La app publica el puerto **8080**.
- Ajusta `SPRING_PROFILES_ACTIVE` en `docker-compose.yml` si defines perfiles (e.g., `dev`, `prod`).
- Para DB, expón `DB_HOST`, `DB_USER`, `DB_PASS`, `DB_NAME` cuando implementes el adaptador.

## CI (GitHub Actions)
- Al hacer push o PR, se ejecuta `mvn -B -DskipTests=false verify`.
- Puedes añadir cobertura, checkstyle o tests de contrato.

---

### (Opcional) Cómo agregar un adaptador Postgres
1. Crea `RepositorioCotizacionPostgres` implementando `RepositorioCotizacionPort` con Spring Data (JDBC/JPA).
2. Usa entidades JPA o mapeo manual, y define `schema.sql`/`data.sql` en `src/main/resources`.
3. Activa el adaptador con un `@Bean` condicionado por perfil (p.ej., `@Profile("db")`).
4. Levanta con `SPRING_PROFILES_ACTIVE=db` para que el caso de uso persista en Postgres.