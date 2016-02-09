# Mockenize
The complete app for mockBean your responses.

Mockenize is an efficient server to mockBean, supports random responses, timeout and any media type. To store the responses, Mockenize uses Hazelcast and to run the standalone server uses Spring Boot.
Mockenize is free and open source. Contribute if you want!

[![Build Status](https://travis-ci.org/Mockenize/mockenize.svg?branch=master)](https://travis-ci.org/Mockenize/mockenize)
# How to use

- Running the server
```json
java -jar mockenize-x.x.x.jar --server.port=8090 (default port is 8080)
```

- Adding basic responses mockBeen

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

- Adding multiple responses mockBeen. The responses will return at random.

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

- Clearing all responses mockBeen

```json
DELETE http://localhost:8080/_mockenize/clearAll
```

- Clearing unique or multiple response mockBean

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

- Use the admin to easily create your mockBeen.

```json
http://localhost:8080/_mockenize/index.html
```
![Mockenize](http://i.imgur.com/yda2C8u.png)
