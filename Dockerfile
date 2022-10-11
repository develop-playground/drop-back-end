FROM openjdk:11-jdk
LABEL description="daily map API server by Dev.Playground"

ENV APP_HOME="/usr/app/"

WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
