FROM maven:3-sapmachine-22 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk
# Copy the executable JAR from the first stage
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "--enable-preview", "-jar", "demo.jar"]