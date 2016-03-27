# Mockenize
REST API Testing Toolkit

Mockenize is an efficient server to mockBean, supports random responses, timeout and any media type. To store the responses, Mockenize uses Hazelcast and to run the standalone server uses Spring Boot.
Mockenize is free and open source. Contribute if you want!

[![Build Status](https://travis-ci.org/Mockenize/mockenize-server.svg?branch=master)](https://travis-ci.org/Mockenize/mockenize-server)
[ ![Download](https://api.bintray.com/packages/mockenize/mockenize/mockenize-server/images/download.svg) ](https://bintray.com/mockenize/mockenize/mockenize-server/_latestVersion)
# How to use

- Running the server
```json
java -jar mockenize-server-x.x.x.jar --server.port=8090 (default port is 8080)
```

- You can use with docker too!

```json
docker run -itd -p 8080:8080 mockenize/mockenize-server
```

- Adding basic mocks

```json
POST http://localhost:8080/_mockenize/mocks
{
  "path" : "/foo/bar",
  "method" : "POST",
  "status" : 202,
  "body" : "Response success!!!",
  "headers" : {
      "Content-Type" : "text/plain"
  }
}
```

- Adding multiple responses mocks. The responses will return at random.

```json
POST http://localhost:8080/_mockenize/mocks
{
  "path" : "/foo/bar",
  "method" : "POST",
  "values" : [
    {
      "status" : 202,
      "body" : "Response success!!!",
      "headers" : {
          "Content-Type" : "text/plain"
      }
    },
    {
      "status" : 500,
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
POST http://localhost:8080/_mockenize/mocks
{
  "path" : "/foo/bar",
  "method" : "POST",
  "timeout" : 5,
  "status" : 202,
  "body" : "Response success!!!",
  "headers" : {
      "Content-Type" : "text/plain"
  }
}
```

- Using interval timeout response. (5 at 10 seconds)

```json
POST http://localhost:8080/_mockenize/mocks
{
  "path" : "/foo/bar",
  "method" : "POST",
  "minTimeout" : 5,
  "maxTimeout" : 10,
  "status" : 202,
  "body" : "Response success!!!",
  "headers" : {
      "Content-Type" : "text/plain"
  }
}
```

- Clearing all mocks.

```json
DELETE http://localhost:8080/_mockenize/mocks/all
```

- Clearing unique or multiple mocks.

```json
DELETE http://localhost:8080/_mockenize/mocks

[
  {
    "path" : "/foo/bar",
    "method" : "POST"
  },
  {
    "url" : "/my_second_url/test",
    "method" : "GET"
  }
]
```

- Use the admin to easily create your mocks.

```json
http://localhost:8080/_mockenize/admin/index.html
```
![Mockenize](http://i.imgur.com/Yxu9dPO.png)
