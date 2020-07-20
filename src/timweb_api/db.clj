(ns timweb-api.db
  "Database config and operations"
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [timweb-api.config :refer [db-conn]]))

(defn all-brands
  "Retrieves all brands from the database"
  []
  (let [sqlmap {:select [:id :name_ru :name_en :logo]
                :from   [:brands]}]
    (jdbc/query db-conn (sql/format sqlmap))))

(defn user-by-login
  "Retrieves user by login"
  [login]
  (let [sqlmap {:select [:id :login :password]
                :from   [:users]
                :where  [:= :login login]}]
    (jdbc/query db-conn (sql/format sqlmap))))