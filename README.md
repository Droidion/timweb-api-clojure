# API for TIM Group implemented in Clojure

Experimental implementation of an API in Clojure ecosystem. For learning purposes.

## Configuration

- Create `resources/config.edn` file
- Set up database connection like that:

```clojure
{:username      "your-db-username"
 :password      "your-db-password"
 :port-number   5432
 :database-name "your-db-name"
 :server-name   "host.docker.internal"}
```

Mind that you if use the port other than 5432, you should change it in `docker-compose.yml`. 

## Usage

### Docker

- Have docker and docker-compose installed
- Have database connection configured in `resources/config.edn`
- `$ docker-compose up -d`
- API is available on port `7888`.

### Run manually

- Have JVM, Clojure and Leiningen installed
- Have Postgres database installed and configured in `resources/config.edn`
- Run
- API is available on port `7888`.

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
- TODO Try insering, updating and deleting
- TODO CI/CD
- TODO Swagger
- TODO Implement all requests