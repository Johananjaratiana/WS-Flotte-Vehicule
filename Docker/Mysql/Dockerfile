FROM mysql:5.7

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=flotte_vehicule

COPY init.sql /docker-entrypoint-initdb.d/init.sql