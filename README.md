# Gradle Fhir Ingestion

Simple Java boilerplate API for ingesting FHIR patient data.

Features a handful of test endpoints.

To run:
- ./gradlew build
- docker build -t spring-fhir-ingestion .
- docker run -it -p 8080:8080 spring-fhir-ingestion
