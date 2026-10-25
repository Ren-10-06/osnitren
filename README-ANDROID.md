# REN OSINT Android app

This project packages the REN OSINT website into an Android WebView app. The HTML interface is bundled in the APK; online lookups and Telegram still require an internet connection.

## Automated APK build

A GitHub Actions workflow builds a debug APK when code is pushed to the `android-app` branch, and can also be started manually.

1. Open the repository's **Actions** tab.
2. Choose **Build REN OSINT Android APK**.
3. Open the latest successful run.
4. Under **Artifacts**, download `REN-OSINT-debug-APK`.
5. Extract the ZIP and install `app-debug.apk` on your Android phone.

This is a debug APK for personal testing, not a Play Store-signed release.
