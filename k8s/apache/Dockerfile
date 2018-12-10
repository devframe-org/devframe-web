FROM devframe-apache

USER root

ADD conf/httpd.conf /usr/local/apache/conf/
ADD conf/workers.properties /usr/local/apache/conf/

EXPOSE 80 443

CMD ["/usr/local/apache/bin/apachectl", "-DFOREGROUND"]