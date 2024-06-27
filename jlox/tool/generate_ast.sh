#!/bin/bash

mvn clean package
java -jar target/generate_ast-0.1.0-SNAPSHOT.jar ../src/main/java/com/craftinginterpreters/lox/
