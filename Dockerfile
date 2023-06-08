FROM openjdk:17

COPY target/Blackjack-1.0-SNAPSHOT.jar Blackjack-1.0-SNAPSHOT.jar

EXPOSE 8102

ENTRYPOINT ["java", "-jar", "/Blackjack-1.0-SNAPSHOT.jar"]