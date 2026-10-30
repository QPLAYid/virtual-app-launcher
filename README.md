# Virtual App Launcher

Aplikasi Android launcher sederhana yang dapat:
- membuka aplikasi tertentu via package name
- membuka Play Store
- membuka URL website
- siap dibuild menjadi APK

## Struktur project

- `app/src/main/java/com/example/virtualapplauncher/MainActivity.kt`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/AndroidManifest.xml`

## Cara build

1. Install Android Studio.
2. Buka folder project ini.
3. Tunggu Gradle sync selesai.
4. Pilih `Build > Build Bundle(s)/APK(s) > Build APK`.
5. APK akan dibuat di folder `app/build/outputs/apk/debug/` atau `app/build/outputs/apk/release/`.

## Catatan

Fitur ini hanya dapat membuka aplikasi Android yang sudah terinstal di perangkat, serta membuka URL atau Play Store. Aplikasi Android tidak bisa menjalankan game PC atau file `.exe` secara langsung.
