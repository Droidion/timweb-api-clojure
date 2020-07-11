# API for TIM Group implemented in Clojure

Experimental implementation of an API in Clojure ecosystem. For learning purposes.

## Configuration

- Create `resources/config.edn` file
- Set up database connection like that:

```clojure
{:db {:username      "denis.rodionov"
      :password      ""
      :port-number   5432
      :database-name "tim"
      :server-name   "localhost"}
 :server {:port 7888}}
```

Mind that if you use the port other than 5432 for the database or other than 7888 for the web server, you should change it in `docker-compose.yml` and `Dockerfile`. 

## Usage

### Docker

- Have docker and docker-compose installed
- Have server port configured in `resources/config.edn`
- Have database connection configured in `resources/config.edn`
- `$ docker-compose up -d`
- API is available on port you specified in the config.

### Run manually

- Have JVM, Clojure and Leiningen installed
- Have server port configured in `resources/config.edn`
- Have Postgres database installed and configured in `resources/config.edn`
- Run
- API is available on port you specified in the config.

```
$ lein deps
$ lein run
```

## Roadmap
- DONE Try reading from DB
- DONE Try Routing
- DONE Try JSON response
- DONE Dockerize
- TODO Try authentication with Auth0
- TODO Try spec
- TODO Try unit tests
- TODO Try inserting, updating and deleting
- TODO CI/CD
- TODO Swagger
- TODO Implement all requests