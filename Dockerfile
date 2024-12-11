FROM maven:3.9.4-eclipse-temurin-17
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install
CMD ["mvn", "test"]
