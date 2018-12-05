FROM devframe-tomcat

USER tomcat

COPY k8s/tomcat/conf/server.xml $CATALINA_HOME/conf/server.xml
COPY k8s/tomcat/bin/catalina.sh $CATALINA_HOME/bin/catalina.sh

RUN mkdir /data/devframew-web
RUN mkdir -p /data/logs/web

ADD build/libs/devframe-web-1.0.war /data/devframew-web/

RUN cd /data/devframew-web && jar -xvf devframe-web-1.0.war && rm -rf devframe-web-1.0.war

CMD ["catalina.sh", "run"]