# spring-learn

Covers hands-on exercises:

1. **Create a Spring Web Project using Maven** – this project itself (Group
   `com.cognizant`, Artifact `spring-learn`, Spring Web + DevTools).
2. **Spring Core – Load Country from Spring Configuration XML** –
   `country.xml`, `Country.java`, and `displayCountry()` in
   `SpringLearnApplication.java` (runs automatically on startup, check the
   console log for the `Country` bean details).
3. **Hello World RESTful Web Service** – `HelloController`.
4. **REST – Country Web Service** – `CountryController.getCountryIndia()`.
5. **REST – Get country based on country code** – `CountryController.getCountry()`
   + `CountryService`.
6. **Create authentication service that returns JWT** – `AuthenticationController`
   + `SecurityConfig`.

## How to import

1. Copy the `spring-learn` folder into your Week3 folder.
2. In Eclipse: `File > Import > Maven > Existing Maven Projects` and browse
   to the `spring-learn` folder.
3. Right-click the project > `Maven > Update Project` to download the
   dependencies (uses your normal Maven/proxy settings).

## Build from command line

```
mvn clean package
```
(add your proxy flags if needed, e.g. `-Dhttp.proxyHost=... -Dhttp.proxyPort=...`)

## Run

```
mvn spring-boot:run
```
or run `SpringLearnApplication.java` as a Java Application in Eclipse.

The app starts on port **8090** (see `application.properties`). On startup
you should see the `Country` bean logged (exercise 2) in the console.

## Try each exercise

**Hello World** (no auth required)
```
curl -s http://localhost:8090/hello
```
Response: `Hello World!!`

**Country web service** (no auth required)
```
curl -s http://localhost:8090/country
```
Response: `{"code":"IN","name":"India"}`

**Get country by code** — requires the `USER` role (see Security below)
```
curl -s -u user:pwd http://localhost:8090/countries/in
```
Response: `{"code":"IN","name":"India"}` (code match is case-insensitive)

**Authenticate and get a JWT**
```
curl -s -u user:pwd http://localhost:8090/authenticate
```
Response: `{"token":"<a JWT string>"}`

Use the returned token as a bearer token in your own client code, or decode
it at https://jwt.io to see the header/payload/signature structure discussed
in the hands-on material.

## Security notes

Two in-memory users are configured in `SecurityConfig`:

| Username | Password | Role  |
|----------|----------|-------|
| admin    | pwd      | ADMIN |
| user     | pwd      | USER  |

`/countries/**` requires role `USER`. `/authenticate` accepts either
`USER` or `ADMIN`. `/hello` and `/country` are left open (`permitAll`) so
you can try them without credentials first, then layer in the secured
endpoints.
