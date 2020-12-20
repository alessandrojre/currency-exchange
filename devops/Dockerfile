FROM openjdk:11-jre-slim

MAINTAINER Alessandro Riega Estrada <alessandro.riega@gmail.com>

ARG artifact_id
ARG artifact_version

ENV artifact ${artifact_id}-${artifact_version}.jar

# Create app directory
RUN mkdir -p /usr/src/app/${artifact_id}
WORKDIR /usr/src/app/${artifact_id}

COPY target/${artifact} /usr/src/app/${artifact_id}/${artifact}

EXPOSE 8080

CMD exec java -Duser.timezone=America/Lima -XX:+PrintFlagsFinal $JAVA_OPTIONS  -jar ${artifact}                
