# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
# You can cache dependencies by splitting copy, but keeping simple for class use
RUN mvn -q -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV JAVA_OPTS=""
# assuming the built jar ends up as target/*-SNAPSHOT.jar or similar
COPY --from=build /workspace/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]