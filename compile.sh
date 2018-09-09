#!/bin/bash

mvn clean compile test

rm -rf spring-boot-generate/src/main/resources/migrations/
rm -rf spring-scaffold-cli/src/main/java/br/com/example/
rm -rf spring-scaffold-cli/src/main/resources/application.properties
rm -rf spring-scaffold-cli/src/main/resources/templates/user/
rm -rf spring-scaffold-cli/src/main/resources/templates/usermodel/
rm -rf spring-scaffold-cli/src/main/resources/templates/layout.html
rm -rf spring-scaffold-cli/src/main/java/com/
