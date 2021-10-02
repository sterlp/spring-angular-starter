[![Java CI with Maven](https://github.com/sterlp/spring-angular-starter/actions/workflows/maven.yml/badge.svg)](https://github.com/sterlp/spring-angular-starter/actions/workflows/maven.yml)

# Overview

The example has delays enabled by default to demonstrat the loadingbar functionality. It can be turned off in the application yml.

- Blog Post: http://sterl.org/2019/02/angular-spring-boot/
- Bootstrap & Material version: https://github.com/sterlp/spring-angular-starter/tree/master
- Old (simple) Material version: https://github.com/sterlp/spring-angular-starter/tree/simple-material

# Frontend
## Install
- Nodejs required
- angular cli required

## How to run
### Dev mode
```bash
npm start
```

# Backend
- Run Spring boot in e.g. STS
- Command Line

## How to run command line
```bash
mvn clean install
cd backend
mvn spring-boot:run
```

# Build & Run
```bash
mvn package
java -jar backend/target/backend-0.1.0-SNAPSHOT.jar
```

# Sidenotes

- `mvn clean` will also delete all node_modules

# Links
- Angular Template: https://github.com/sterlp/admin-template
- CoreUI 3.0 Doc: https://coreui.io/docs/getting-started/introduction/
- Angular Material: https://material.angular.io/components/categories
- Setup a proxy: https://medium.com/@spencerfeng/setup-a-proxy-for-api-calls-for-your-angular-cli-app-6566c02a8c4d
- All material modules: https://gist.github.com/pimatco/d5b1891feb90b60ca4681011b6513873
- Link to Angular: https://angular.io/guide/quickstart
- https://ng-bootstrap.github.io
