FROM openjdk:8-jre

EXPOSE 8080

ADD ./target/*.jar ionic-chat-client-server.jar

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /ionic-chat-client-server.jar