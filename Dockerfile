FROM maven:3.5.2-jdk-8 AS build-env
WORKDIR /app
COPY . /app
RUN mvn package

FROM tomcat:8
VOLUME /tmp
COPY --from=build-env /app/target/ROOT.jar /usr/local/tomcat/webapps/ROOT.jar
RUN sh -c 'touch /usr/local/tomcat/webapps/ROOT.jar'
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/ROOT.jar"]