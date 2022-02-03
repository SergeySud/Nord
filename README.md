# Nord

App supports swagger UI at  `swagger-ui/index.html`.

POST `/add` with JSON load `"url":*{url}*` adds a new link.

GET `/*{uuid}*` redirects to the url.

GET `/all` shows all links. Requires user to be authenticated.

DELETE `/*{uuid}*` deletes a link. Requires user to be authenticated.


You can run command `.\gradlew bootRun` for Win or `./gradlew bootRun` for Linux/MacOs to start the app.
