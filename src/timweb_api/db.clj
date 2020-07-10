(ns timweb-api.db
  "Database config and operations"
  (:require [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as cp]))

(def datasource-options
  {:username           "denis.rodionov"
   :password           ""
   :port-number        5432
   :database-name      "tim"
   :server-name        "localhost"
   :auto-commit        true
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

(defonce datasource
         (delay (cp/make-datasource datasource-options)))

(def database-connection {:datasource @datasource})

(defn all-brands
  "Retrieves all brands from the database"
  []
  (jdbc/query database-connection ["SELECT id, name_ru, name_en, logo FROM brands;"]))