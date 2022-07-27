FROM openjdk:11-jdk
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
VOLUME ["/app"]
COPY build/libs/dayofglory-0.0.1-SNAPSHOT
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
