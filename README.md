# BidMart Auth Service

`bidmart-auth-service` adalah service khusus untuk authentication dan authorization pada arsitektur microservice BidMart. Repository ini disiapkan sebagai tahap awal strangler pattern dari monolith (`Bidmart` branch `feat/auction-query-rollout`) agar pemisahan auth dapat dilakukan bertahap tanpa rewrite total.

## Fungsi Service

Service ini menjadi owner untuk:

- registrasi dan login user,
- pengelolaan token (access + refresh),
- logout / revocation session,
- data user profile untuk kebutuhan identitas,
- role/permission untuk authorization,
- endpoint internal validasi token dan query permission.

## Data Ownership

Auth service **memiliki** data berikut:

- user account,
- credential (password hash, bukan plain text),
- role,
- permission,
- token/session metadata.

Service lain **dilarang** mengakses database auth secara langsung. Akses harus melalui API contract auth service.

## API Contract (Minimal)

Endpoint berikut sudah disiapkan sebagai contract awal (scaffold controller + DTO):

- `POST /auth/register`
- `POST /auth/login`
- `POST /auth/refresh`
- `POST /auth/logout`
- `GET /users/{userId}/profile`
- `POST /internal/auth/validate-token`
- `GET /internal/users/{userId}/permissions`

> Status implementasi saat ini: semua endpoint masih **TODO response** (belum persistence/JWT production-ready).

## Struktur Penting

- `src/main/java/.../api/AuthController.java` → endpoint auth publik.
- `src/main/java/.../api/UserController.java` → endpoint profile user.
- `src/main/java/.../internal/InternalAuthController.java` → endpoint internal service-to-service.
- `src/main/java/.../api/dto/*` → request/response contract awal.

## Dependency ke Database

Contoh konfigurasi disediakan di `application-example.yml` (tanpa secret) menggunakan PostgreSQL untuk schema auth.

## Run Lokal

```bash
./gradlew bootRun
```

Default port: `8083`.

## Test

```bash
./gradlew test
```

## Service yang Bergantung ke Auth

- `bidmart-gateway` untuk login/register dan token forwarding selama masa transisi.
- Seluruh backend service (`listing-query`, `auction-query`, `bidding-command`, `wallet`, `notification`) untuk validasi token internal dan permission check.

## Coupling yang Masih Tersisa (Harus Diputus Bertahap)

Berikut coupling yang diperkirakan masih ada pada monolith sumber (asumsi berdasarkan konteks migrasi; perlu verifikasi kode lintas-repo):

1. Flow register kemungkinan masih memicu provisioning domain lain (mis. wallet) secara langsung.
2. Authorization check kemungkinan masih tersebar di controller/service domain non-auth.
3. Session/token revocation kemungkinan belum dipusatkan di auth service.

## TODO Implementasi Lanjutan

1. Tambah persistence layer (`User`, `Role`, `Permission`, `SessionToken`) + migration schema.
2. Implementasi hashing password dan policy keamanan (rate limit/login attempt lock).
3. Implementasi JWT signing + key rotation + refresh token rotation.
4. Implementasi endpoint internal validate-token dan permissions secara real.
5. Pindahkan logic auth/user dari monolith ke service ini dan update gateway routing.

## Catatan Keamanan

Jangan commit file berikut:

- `.env`, credential, token, private key,
- `build/`, `target/`, `node_modules/`,
- file lokal IDE.
