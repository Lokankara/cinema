FROM debian:buster-slim
RUN apt-get update && apt-get install -y nginx  && rm -rf /var/lib/apt/lists/*
EXPOSE 80
COPY custom.conf /etc/nginx/conf.d/default.conf
CMD ["nginx","-g","daemon off;"]

FROM debian:latest

RUN apt-get update && apt-get install -qq -y \
    shellcheck \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app/

COPY . /usr/src/app/

CMD ["docker-compose","up","--force-recreate"]
