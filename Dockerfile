FROM devframe-tomcat

USER root

COPY k8s/tomcat/server.xml $CATALINA_HOME/conf/server.xml
COPY k8s/tomcat/catalina.sh $CATALINA_HOME/bin/catalina.sh

RUN mkdir -p /data/web
RUN mkdir -p /data/logs/web

ADD build/libs/devframe-web-1.0.war /data/web/

RUN cd /data/web && jar -xvf devframe-web-1.0.war && rm -rf devframe-web-1.0.war

CMD ["catalina.sh", "run"]