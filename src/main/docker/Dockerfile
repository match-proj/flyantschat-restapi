FROM insideo/jre8:latest
ENV JAR_NAME flyantschat-restapi.jar
ENV HOME_PATH /home

ADD /$JAR_NAME $HOME_PATH/app.jar

WORKDIR $HOME_PATH

ENTRYPOINT ["java","-jar","app.jar"]