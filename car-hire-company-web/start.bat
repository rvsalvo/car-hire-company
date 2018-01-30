@echo off
SET JAVA_HOME=E:\Java\jdk1.8.0_310
%JAVA_HOME%\bin\java -Xmx512m -agentlib:jdwp=transport=dt_socket,address=8010,server=y,suspend=n -Dclient.config=. -Dlog4j.configuration=file:./conf/log4j.properties -jar ./target/simple-client.jar