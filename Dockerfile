FROM payara/server-full

USER root
RUN apt-get update
RUN apt-get -y install curl
RUN curl https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.41/mysql-connector-java-5.1.41.jar -o /opt/payara/appserver/glassfish/domains/production/lib/ext/mysql-connector-java-5.1.41.jar

COPY EJBank-ear/target/EJBank.ear $DEPLOY_DIR

RUN echo 'create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property User=root:Port=3306:Password=root:Url="jdbc\:mysql\://db\:3306\/ejbank" EJBankPool' \
 > $POSTBOOT_COMMANDS

RUN echo 'create-jdbc-resource --connectionpoolid EJBankPool jdbc/EJBankDS' \
 >> $POSTBOOT_COMMANDS

EXPOSE 8181