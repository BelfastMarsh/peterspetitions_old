# Use the Tomcat image as the base image
FROM tomcat:latest

# Copy your WAR file into the Tomcat webapps directory
COPY ./target/peterspetitions.war /usr/local/tomcat/webapps

COPY ${TOMCAT_FILE_PATH}/* ${CATALINA_HOME}/conf/



# Expose the port the Tomcat server will be listening on
EXPOSE 9090
ENTRYPOINT ["catalina.sh", "run"]
