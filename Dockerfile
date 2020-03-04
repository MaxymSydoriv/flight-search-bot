FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/flight-search-bot.jar
ADD ${JAR_FILE} flight-search-bot-0.1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/flight-search-bot-0.1.0.jar"]