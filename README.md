# Mockenize
The complete app for mock your responses

# Notes
Mockenize uses Hazelcast (hazelcast.com) to store your mocks and Spring Boot (projects.spring.io/spring-boot) for standalone server

# Basic use

- Running the server
java -jar mockenize-x.x.x.jar --server.port=8090 (default port is 8080)

- Adding responses mocks

POST http://localhost:8080/_mockenize
{
  "url" : "/my_url/test",
  "responseCode" : 202,
  "response" : "Response success!!!"
}

- Clearing all responses mocks

DELETE http://localhost:8080/_mockenize/clearAll

- Clearing unique or multiple response mock

DELETE http://localhost:8080/_mockenize

[
  {
    "url" : "/my_url/test"
  },
  {
    "url" : "/my_second_url/test"
  }
]
