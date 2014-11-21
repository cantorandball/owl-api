# Owl API Prototype

Prototype of a 

Requirements:

  * [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
  * Scala 2.11.1
  * SBT

## Build & Run ##

```sh
$ MEASUREMENTS_API_KEYS_LOCATION=src/test/resources/test-api-keys.properties MEASUREMENTS_API_DATABASE_PLATFORM=h2 sbt
> container:start
> browse
```
