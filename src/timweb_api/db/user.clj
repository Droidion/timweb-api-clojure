(ns timweb-api.db.user
  "DB operations with users"
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [timweb-api.config :refer [db-conn]]))

(defn user-by-login
  "Retrieves user by login"
  [login]
  (let [sqlmap {:select [:id :login :password]
                :from   [:users]
                :where  [:= :login login]}]
    (jdbc/query db-conn (sql/format sqlmap))))