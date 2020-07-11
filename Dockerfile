FROM clojure:openjdk-15-alpine
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
# Load deps into build, should auto fetch new deps
COPY project.clj /usr/src/app/
RUN lein deps
COPY . /usr/src/app
EXPOSE 7888
CMD lein run