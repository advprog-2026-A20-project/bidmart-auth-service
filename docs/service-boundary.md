# Auth Service Boundary

## Tanggung Jawab

- Issue token untuk user yang berhasil login.
- Menyimpan credential dan role.
- Menyediakan data user minimum untuk gateway dan service internal.

## Tidak Ditangani

- Saldo wallet.
- Bid placement.
- Listing dan auction lifecycle.
- Notification delivery.

## Kontrak Minimal

```http
POST /api/auth/register
POST /api/auth/login
GET /api/auth/me
POST /internal/tokens/introspect
GET /internal/users/{userId}
```

## Asumsi

Logic lama masih berada di gateway sampai token contract dan wallet side effect dipisah dengan aman.
