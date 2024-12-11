FROM maven:3.8.7-openjdk-14-slim AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install
CMD ["mvn", "test"]
