FROM tomcat:9.0-jdk17-openjdk

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/sales-crud.war /usr/local/tomcat/webapps/sales-crud.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
