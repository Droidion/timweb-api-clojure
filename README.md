# API for TIM Group implemented in Clojure

Experimental implementation of an API in Clojure ecosystem. For learning purposes.

## Usage

- Have JVM, Clojure and Leiningen installed
- Have Postgres database installed and configured
- Run the REPL and start the server with `(server)`

## How to configure the database

- Create `resources/config.edn` file
- Have the following db connection properties:

```clojure
{:username      "your-db-username"
 :password      "your-db-password
 :port-number   5432
 :database-name "your-db-name"
 :server-name   "localhost"}
```