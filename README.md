# Owl API Prototype

Prototype of a API for Personal History project.

Requirements:

  * [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
  * Scala 2.11.1
  * SBT
  * Ffmpeg

## Build & Run ##

```sh
$ sbt run
```

## Infrastructure ##

To install the web service on Ubuntu you need to create the following Upstart script:

```
description "Owl API"

start on runlevel [2345]
stop on runlevel [016]

console log

setuid owl-api
setgid owl-api

chdir /home/owl-api/owl-api

exec sh /home/owl-api/owl-api/bin/owl-api
```

Then add the following user:

```sh
$ adduser owl-api
```

Then add the following configuration file to the `/etc/nginx/sites-enabled/owl-api.conf` folder:

```
server {
  listen 80;
  server_name api.ph.cantorandball.com;
  client_max_body_size 100M;


  location / {
    proxy_pass http://127.0.0.1:8080;
    proxy_set_header Host $http_host;
  }
}
```

## Deploy ##

To deploy execute the following commands:

```sh
$ scp target/owl-api-1.0.0-SNAPSHOT.zip root@151.236.220.149:
$ ssh root@151.236.220.149
$ unzip owl-api-1.0.0-SNAPSHOT.zip
$ mv owl-api /home/owl-api/
```
