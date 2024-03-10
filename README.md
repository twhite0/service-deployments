# Service Deployments

Consumer and Provider services to assist with deployment testing.

## Building

`./gradlew clean build`

## Startup

Provider shell

`java -jar provider/build/libs/provider.jar --spring.profiles.active=local`

Consumer shell 

`java -jar consumer/build/libs/consumer.jar --spring.profiles.active=local`

## Testing

`curl localhost:50000/provider`

`curl localhost:40000/consumer`

## TODO

- Test cases
