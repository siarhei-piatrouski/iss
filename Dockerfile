FROM openjdk:17

WORKDIR /var/iss/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw package -DskipTests

RUN cp /var/iss/app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]