# Mockenize
The complete app for mock your responses

# Notes
Mockenize uses Hazelcast (hazelcast.com) to store your mocks and Spring Boot (projects.spring.io/spring-boot) for standalone server

# Basic use

- Running the server
```json
java -jar mockenize-x.x.x.jar --server.port=8090 (default port is 8080)
```

- Adding basic responses mocks

```json
POST http://localhost:8080/_mockenize
{
  "url" : "/my_url/test",
  "method" : "POST",
  "responseCode" : 202,
  "body" : "Response success!!!",
  "headers" : {
      "Content-Type" : "text/plain"
  }
}
```

- Adding multiple responses mocks. The responses will return at random.

```json
POST http://localhost:8080/_mockenize
{
  "url" : "/my_url/test",
  "method" : "POST",
  "values" : [
    {
      "responseCode" : 202,
      "body" : "Response success!!!",
      "headers" : {
          "Content-Type" : "text/plain"
      }
    },
    {
      "responseCode" : 500,
      "body" : "Response fail!!!",
      "headers" : {
          "Content-Type" : "text/plain"
      }
    }
  ]
}
```

- Using timeout response. (5 seconds)

```json
POST http://localhost:8080/_mockenize
{
  "url" : "/my_url/test",
  "method" : "POST",
  "timeout" : 5,
  "responseCode" : 202,
  "body" : "Response success!!!",
  "headers" : {
      "Content-Type" : "text/plain"
  }
}
```

- Using interval timeout response. (5 at 10 seconds)

```json
POST http://localhost:8080/_mockenize
{
  "url" : "/my_url/test",
  "method" : "POST",
  "minTimeout" : 5,
  "maxTimeout" : 10,
  "responseCode" : 202,
  "body" : "Response success!!!",
  "headers" : {
      "Content-Type" : "text/plain"
  }
}
```

- Clearing all responses mocks

```json
DELETE http://localhost:8080/_mockenize/clearAll
```

- Clearing unique or multiple response mock

```json
DELETE http://localhost:8080/_mockenize

[
  {
    "url" : "/my_url/test"
  },
  {
    "url" : "/my_second_url/test"
  }
]
```
