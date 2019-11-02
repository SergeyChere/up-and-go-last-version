FROM openjdk:8
COPY target/coffee-shop.jar coffee-shop.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "coffee-shop.jar"]
