FROM amazoncorretto:21.0.7-alpine

LABEL authors="cw-solaligue"

ARG DEPENDENCY=""
ARG JAR_NAME=""

ENV SPRING_PROFILE=""
ENV JAVA_OPTS=""
ENV AZURE_CLIENT_ID=""
ENV AZURE_CLIENT_SECRET=""
ENV AZURE_TENANT_ID=""

COPY ${DEPENDENCY}/${JAR_NAME} /app/app.jar

ENTRYPOINT exec java ${JAVA_OPTS} -cp /app/app.jar org.springframework.boot.loader.launch.JarLauncher --spring.profiles.active=${SPRING_PROFILE}