# Yelp Sample app.

## Design/Architecture considerations
- The chosen architecure is Model-View-ViewModel
- Because of the nature of the app. Search -> Results -> Details. The model is not persistent
- Searches are cached for 15 minutes.
- App is implemented using Jetpack's architecture components.
  - Live Data
  - Navigation 
  - ViewModel
  - Databinding
- Dependency Injection using dagger 2.

## Running
- Yelp Client Id and API Key must be included in `local.properties`
  - Example:
  ```
    yelpClientId="CLIENT_ID"
    yelpAPIKEY="API_KEY"
  ```

## Tests
- Unit tests and integration tests are included.

## TODO/Out of Scope
- Yelp search endpoint requires lat/lon, getting the user's location is not implemented and location is Defaulted to NYC. (Lots of businesses)
- Search list will show up to 50 results. Paging/Infinite scroll is not implemented.
- Espresso Idling resources are not implemented.
- Test coverage is not complete.
