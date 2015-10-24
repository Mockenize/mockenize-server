FROM mauricioadlima/mockenize:latest
MAINTAINER Mauricio Lima (mauricioadlima@gmail.com)
WORKDIR /opt
RUN ["rm", "/opt/startup.sh"]
RUN ["rm", "/opt/mockenize-0.0.1.jar"]
ADD mockenize-0.0.2.jar /opt/
ADD startup.sh /opt/
RUN ["chmod", "+x", "/opt/startup.sh"]
ENTRYPOINT ["/opt/startup.sh"]
EXPOSE 8080
