FROM maven:3.5.3-jdk-8
MAINTAINER Rodrigo Ruiz, https://github.com/rodrigoruizbaca
WORKDIR /usr/local/src/
COPY Gateway-0.0.1-SNAPSHOT-spring-boot.jar /usr/local/src/
COPY wait-for-it.sh /usr/local/src/
RUN chmod +x /usr/local/src/wait-for-it.sh
CMD java -jar Gateway-0.0.1-SNAPSHOT-spring-boot.jar
EXPOSE 8094 8094
