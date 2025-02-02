FROM payara/server-full:5.2020.6

USER root

# Install mysql driver
RUN apt-get update
RUN apt-get -y install curl
RUN curl https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.41/mysql-connector-java-5.1.41.jar -o /opt/payara/appserver/glassfish/domains/production/lib/ext/mysql-connector-java-5.1.41.jar

# Create database pool
RUN echo 'create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property User=root:Port=3306:Password=root:Url="jdbc\:mysql\://db\:3306\/ejbank" EJBankPool' \
 > $POSTBOOT_COMMANDS

# Create JNDI link to our database pool
RUN echo 'create-jdbc-resource --connectionpoolid EJBankPool jdbc/EJBankDS' \
 >> $POSTBOOT_COMMANDS

# Enable autodeploy
RUN echo 'set server.admin-service.das-config.dynamic-reload-enabled=true' \
 >> $POSTBOOT_COMMANDS
RUN echo 'set server.admin-service.das-config.autodeploy-enabled=true' \
 >> $POSTBOOT_COMMANDS

EXPOSE 8181

