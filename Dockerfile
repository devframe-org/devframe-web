FROM devframe-tomcat

USER root

COPY k8s/tomcat/conf/server.xml $CATALINA_HOME/conf/server.xml
COPY k8s/tomcat/conf/catalina.sh $CATALINA_HOME/bin/catalina.sh

RUN mkdir -p /data/app/web
RUN mkdir -p /data/logs/web

ADD build/libs/devframe-web-1.0.war /data/app/web/

RUN cd /data/app/web && jar -xvf devframe-web-1.0.war && rm -rf devframe-web-1.0.war

CMD ["catalina.sh", "run"]