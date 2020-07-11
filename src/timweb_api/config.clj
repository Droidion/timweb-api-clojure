(ns timweb-api.config
  "Configuring the app"
  (:require [aero.core :as aero]
            [hikari-cp.core :as cp]
            [mount.core :refer [defstate]]))

(def default-db-options
  "Non auth related database config options"
  {:auto-commit        true
   :read-only          false
   :adapter            "postgresql"
   :connection-timeout 30000
   :validation-timeout 5000
   :idle-timeout       600000
   :max-lifetime       1800000
   :minimum-idle       10
   :maximum-pool-size  20
   :pool-name          "db-pool"
   :register-mbeans    false})

(defn- read-config
  "Reads app-level config from edn file"
  []
  (aero/read-config (clojure.java.io/resource "config.edn")))

(defn- construct-db-config
  "Merges config loaded from file with the default config"
  []
  (merge default-db-options (get (read-config) :db)))

;; Create HikariCP connection pool
(defonce datasource
         (delay (cp/make-datasource (construct-db-config))))

(defn- get-conn
  "Returns db connection as connection pool"
  []
  {:datasource @datasource})

;; db connection in state
(defstate db-conn :start (get-conn))

;; server config in state
(defstate server-config :start (get (read-config) :server))