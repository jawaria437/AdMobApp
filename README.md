##  Overview
This Android application is a **Task Manager** that implements:
- **Firebase Authentication** (Login & Register)
- **Firebase Realtime Database** (For storing ad IDs)
- **AdMob Ads**:
  - App Open Ad
  - Banner Ad
  - Interstitial Ad
- **5 Screen Navigation Flow**
- **MVVM Architecture**
- **Java + XML** Implementation

---

## ✨ Features
1. **Login & Registration**
   - Firebase Authentication (Email/Password)
   - Session management using SharedPreferences
   - User-friendly UI
 **AdMob Integration**
   - **App Open Ad** after splash screen
   - **Banner Ad** at bottom of every screen (except splash)
   - **Interstitial Ad** before moving from Screen 3 to Screen 4
   - Test Ad IDs used (as per Google policy)

 **5 Screens**
   - **Splash Screen** (App Open Ad)
   - **Login Screen**
   - **Register Screen**
   - **Main Screen** (Task List)
   - **Additional Screens** (for demo navigation & ads)

 **Firebase Realtime Database**
   - Stores Ad Unit IDs (or alternatively stored locally)
   - Easy update of ad IDs without app update

---

## 🖼️ Screenshots
| Splash Screen | Login | Register |
|---------------|-------|----------|
| ![Splash](screenshots/splash.png) | ![Login](screenshots/login.png) | ![Register](screenshots/register.png) |

| Main Screen | Ad Example |
|-------------|-----------|
| ![Main](screenshots/main.png) | ![Ads](screenshots/ads.png) |

---

## 🛠️ Tech Stack
- **Language:** Java
- **UI Design:** XML
- **Database:** Firebase Realtime Database
- **Authentication:** Firebase Authentication
- **Ads:** Google AdMob
- **Architecture:** MVVM
- **IDE:** Android Studio
