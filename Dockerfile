# Use the Tomcat image as the base image
FROM tomcat:latest

# Copy your WAR file into the Tomcat webapps directory
ADD ./target/*.war /usr/local/tomcat/webapps/

# Expose the port the Tomcat server will be listening on
EXPOSE 8080
CMD ["catalina.sh", "run"]
