FROM maven:3.6.0-jdk-8
VOLUME /tmp
WORKDIR /root
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN chmod -R 777 /root
COPY / /root/app-data-clean-steam
WORKDIR /root/app-data-clean-steam
RUN mvn clean install -Dmaven.test.skip --settings settings.xml
WORKDIR /root/app-data-clean-steam/target
RUN bash -c 'touch app-data-clean-steam.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app-data-clean-steam.jar"]
