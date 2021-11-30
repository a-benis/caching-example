FROM openjdk:8-jdk-alpine

COPY target/caching-example-0.0.1-SNAPSHOT.jar /usr/app/ROOT.jar

WORKDIR /usr/app

# Expose the specific port your app is running
EXPOSE 4500

ENTRYPOINT ["java", "-jar", "ROOT.jar"]
