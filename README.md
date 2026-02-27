# GoT Characters

Android take-home implementation built with Kotlin and Jetpack Compose.

## Overview

This app fetches and displays Game of Thrones characters from the provided API.

### Features

* Scrollable list of characters
* Search by name, culture, actor, or title
* Displays:

    * full name
    * culture
    * date of death
    * seasons appeared in (shown as Roman numerals)
* Character details screen
* Loading, error, and empty states
* Unit tests and Compose UI tests

---

## Tech Stack

* Kotlin
* Jetpack Compose
* MVVM
* Hilt for dependency injection
* Retrofit + OkHttp + Moshi for networking
* Coroutines + StateFlow
* Navigation Compose
* JUnit / MockK / Truth / Compose UI Test

---

## Architecture

The project uses a clean single-module structure with clear package separation:

* data

    * Retrofit API
    * DTOs
    * mappers
    * repository implementation
* domain

    * models
    * repository contract
    * use cases
* ui

    * screens
    * ViewModels
    * UI state
* core

    * shared utilities

### Why this approach

For a take-home of this size, a single app module keeps the project easy to run and review while still demonstrating separation of concerns, maintainability, and testability.

---

## API Mapping Notes

The real API response uses fields such as:

* name
* titles
* tvSeries
* playedBy

These are mapped into the app’s domain model:

* fullName
* title
* seasons
* actor

### Season handling

The API returns seasons like:

["Season 1", "Season 6"]

These are parsed into integers and displayed in Roman numerals in the UI:

I, VI

### Actor handling

The API does not expose a direct actor field.
It returns playedBy as a list, and the app displays the first non-blank value from that list as the character’s actor.

---

## Setup

### Requirements

* Android Studio
* JDK 17
* Android SDK 35
* Emulator or physical device

### Run the app

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run on an emulator or device

---

## Testing

### Unit tests

Located in:

app/src/test/java/com/example/gotcharacters/

Examples:

* Roman numeral conversion
* DTO to domain mapping
* ViewModel filtering and error handling

### UI tests

Located in:

app/src/androidTest/java/com/example/gotcharacters/

Examples:

* list renders correctly
* empty state renders
* error state renders
* character click callback works

### Run tests

Unit tests:
./gradlew :app:testDebugUnitTest

Instrumented / Compose UI tests:
./gradlew :app:connectedDebugAndroidTest

---

## Tradeoffs / Decisions

### Why Jetpack Compose

Compose keeps the UI concise and modern, and it is a good fit for a take-home because it reduces boilerplate and makes state-driven UI straightforward.

### Why MVVM + Repository

This keeps the UI independent from the data layer and makes the code easier to test and extend.

### Why single-module instead of multi-module

For a small take-home, multi-module architecture would add unnecessary complexity. A single-module app with strong internal package boundaries is a better tradeoff here.

### Why local search

Search is done locally after fetching the dataset once. This keeps the UI responsive and avoids unnecessary network calls.

### Why localId and stableKey exist

The API response does not provide a safe stable unique ID for every character row.
To avoid duplicate list-key issues and ensure safe navigation, the app generates:

* localId for internal navigation
* stableKey for Compose list rendering

---

## What I Would Improve With More Time

* Add offline caching with Room
* Improve accessibility and UI polish
* Add more UI tests and edge-case tests
* Add stronger retry and network error handling
* Improve filtering UX with debounce and better empty-state messaging

---

## Known Limitations

* The API data contains incomplete and inconsistent fields for some characters, so safe fallback values are used where necessary.
* The current implementation uses in-memory caching only.
* Only the first non-blank title and first non-blank actor are shown in the UI for simplicity.

---

## Notes

* The app prioritises clarity, maintainability, and correctness over unnecessary complexity.
* The optional character details screen was included as an enhancement.
