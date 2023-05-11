FROM gradle:8.1-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle web:build --no-daemon

FROM registry.access.redhat.com/ubi8/openjdk-17

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'

COPY --from=build --chown=185 /home/gradle/src/web/build/quarkus-app/lib/ /deployments/lib/
COPY --from=build --chown=185 /home/gradle/src/web/build/quarkus-app/*.jar /deployments/
COPY --from=build --chown=185 /home/gradle/src/web/build/quarkus-app/app/ /deployments/app/
COPY --from=build --chown=185 /home/gradle/src/web/build/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV AB_JOLOKIA_OFF=""
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"