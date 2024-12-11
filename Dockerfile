FROM maven:3.8.7-openjdk-11-slim AS build

# Install JDK 14
RUN apt-get update && apt-get install -y openjdk-14-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set JDK 14 as default
ENV JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install
