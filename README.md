# BidMart Auth Service

`bidmart-auth-service` adalah source of truth untuk akun user, autentikasi, dan JWT pada ekosistem BidMart.

## Endpoint Publik

- `POST /auth/register`
- `POST /auth/login`
- `GET /auth/me`
- `GET /users/{userId}/profile`

## Endpoint Internal

- `POST /internal/auth/validate-token`
- `GET /internal/users/{userId}/permissions`

## Integrasi Wallet

Saat register BUYER, auth service melakukan bootstrap saldo awal ke wallet service.
`/auth/me` dan payload `user` di login mengambil saldo dari wallet service agar wallet tetap menjadi source of truth saldo.

## Environment

Lihat `.env.example`.

Variabel utama:

- `PORT` (default `8081`)
- `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`
- `JWT_SECRET`, `JWT_EXP_SECONDS`
- `CORS_ALLOWED_ORIGINS`
- `WALLET_SERVICE_BASE_URL`

## Local Run

```bash
cp .env.example .env
./gradlew bootRun
```

## Test

```bash
./gradlew test
```

## Docker

```bash
docker build -t bidmart-auth-service .
docker run --env-file .env -p 8081:8081 bidmart-auth-service
```
