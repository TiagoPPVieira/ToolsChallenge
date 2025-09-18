FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

RUN rm -f /etc/localtime && ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime

VOLUME /tmp

COPY target/*.jar /app.jar

CMD java -Xmx$JVM_MEMORY -Djava.security.egd=file:/dev/./urandom -jar /app.jar --spring.config.location=classpath:/$PROPERTIES_SPRING