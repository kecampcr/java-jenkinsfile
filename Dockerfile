#WHY AMAZONCORRETTO? => https://hub.docker.com/_/amazoncorretto
#STAGE:BUILDER
FROM maven:3.8.5-amazoncorretto-11 as builder
WORKDIR /app
COPY pom.xml .
COPY src src

RUN mvn install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#STAGE: RUNNER
FROM amazoncorretto:11-alpine3.15 AS runner
#DEVSECOPS - RUN LAST UPDATES ON CONTAINER
RUN apk update
RUN apk upgrade
EXPOSE 8080
ARG DEPENDENCY=/app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","curso.microservicios.cr.localizacion.LocalizacionApplication"]
#ENTRYPOINT ["sh"] //If error use this entrypoint
