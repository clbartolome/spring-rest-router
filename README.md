# rest-router Project

This project is used to create a bunch of microservices that call between each other for testing puposes in OpenShift.

## Configuration

Done via Environment Variables:

- **APP_NAME**: PostgreSQL host url
- **APP_ROUTING_DESTINATION**: Comma separated routes to call (ex: application-b/route,application-c/route)

## API Documentation & Other Tools

API calls:

- Get all brands [GET]: `/route`. Example
  ```sh
  curl -X GET http://localhost:8080/route
  ```

## Run in Openshift

Use following commands to deploy this model:

```sh
# Create project
oc new-project rest-routing-test

# Create image builder
oc new-build \
  --name rest-router \
  openshift/ubi8-openjdk-11:1.3~https://github.com/clbartolome/spring-rest-router \
  -n rest-routing-test

# Build image
oc start-build rest-router
```

## Run Locally

Requires podman or other container system

```sh

# Start application using Maven
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DAPP_NAME=local-router -DAPP_ROUTING_DESTINATION="

# Validate
curl localhost:8080/route
```

