# BidMart Auth Service

Repository ini adalah target bounded context untuk autentikasi BidMart. Pada fase ini repo masih berupa scaffold service agar migrasi dapat berjalan dengan pola strangler tanpa memindahkan credential flow secara terburu-buru.

## Service Boundary

Auth service akan menangani:

- Registrasi user.
- Login dan penerbitan token.
- Validasi token atau introspection untuk gateway/service internal.
- Data credential dan role user.

Auth service tidak boleh menjadi source of truth saldo wallet. Pembuatan wallet setelah registrasi harus dipindah ke event `UserRegistered` atau kontrak eksplisit dengan wallet service.

## Status Migrasi

Saat ini implementasi auth masih berada di `bidmart-gateway` legacy monolith. Repo ini menyediakan struktur awal, konfigurasi dasar, dan dokumentasi kontrak sebelum logic dipindahkan.

## Run Lokal

```bash
./gradlew bootRun
```

Default port:

```text
8083
```

## Test

```bash
./gradlew test
```

## Dependency Service Lain

- Gateway memanggil auth untuk login/register/me saat migrasi sudah aktif.
- Wallet service menerima event `UserRegistered`, bukan dipanggil langsung dari auth sebagai transaksi lokal.

## Catatan Keamanan

Jangan commit `.env`, credential, token, private key, `build/`, `target/`, atau file lokal IDE.
