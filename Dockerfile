FROM openjdk:17-jdk-alpine3.14

COPY "./target/roleplay.jar" "/application/roleplay.jar"

CMD ["java", "-jar", "/application/roleplay.jar"]
