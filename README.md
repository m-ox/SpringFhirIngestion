# Gradle Fhir Ingestion

Simple Java boilerplate API for ingesting FHIR patient data.

Features a handful of test endpoints.

To run:
1 - ./gradlew build
2 - docker build -t spring-fhir-ingestion .
3 - docker run -it -p 8080:8080 spring-fhir-ingestion
