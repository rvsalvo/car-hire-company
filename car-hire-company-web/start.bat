@echo off
java -Xmx512m -agentlib:jdwp=transport=dt_socket,address=8010,server=y,suspend=n -Dlogging.config=file:./conf/log4j2.yml -jar ./target/car-hire-company-app.jar