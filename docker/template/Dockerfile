FROM amazoncorretto:21.0.7-alpine
LABEL app.author="cw.aranibar.solaligue@gmail.com" \
      app.github="https://github.com/wChris7s" \
      app.version="1.0.0"

WORKDIR /app

ARG SOURCE_DIR_NAME=""
ARG JAR_NAME=""

ENV SPRING_PROFILE=""

COPY ${SOURCE_DIR_NAME} .
COPY ${JAR_NAME} app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${SPRING_PROFILE}", "app.jar"]