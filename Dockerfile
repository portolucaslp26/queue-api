FROM maven:3.8.4-openjdk-17
WORKDIR /app
COPY . .
RUN mvn package
RUN cp target/devel-0.0.1-SNAPSHOT.jar .
RUN ls
CMD ["java", "-jar", "devel-0.0.1-SNAPSHOT.jar"]
