# Spring Boot REST API starter

A small JSON API in Spring Boot 4 and Java 21, ready to deploy on [OceanLaunch](https://oceanlaunch.cloud).

| Endpoint | What it does |
|---|---|
| `GET /` | Lists the endpoints |
| `GET /api/hello?name=you` | Says hello |
| `GET /api/greetings` | The greetings posted so far (kept in memory) |
| `POST /api/greetings` | Adds one: `{"message": "Hi!"}` |
| `GET /actuator/health` | Health check, used by OceanLaunch before it switches to a new version |

## Deploy it

In the OceanLaunch portal, open **Templates** and choose **Spring Boot REST API**. Your app is live at its
`*.apps.oceanlaunch.cloud` address in a few minutes.

## Make it yours

1. On GitHub, choose **Use this template** to get your own copy of this repository.
2. In the portal, open your app, and under **Make it yours** give your new repository's address.
3. Change the code and push: every push to `main` deploys a new version.

## Run it on your computer

```bash
mvn spring-boot:run
```

Then open http://localhost:8080. Run the tests with `mvn test`.
