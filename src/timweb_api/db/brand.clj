(ns timweb-api.db.brand
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [timweb-api.config :refer [db-conn]]))

(defn all-brands
  "Retrieves all brands from the database"
  []
  (let [sqlmap {:select [:id :name_ru :name_en :logo]
                :from   [:brands]}]
    (jdbc/query db-conn (sql/format sqlmap))))

(defn add-brand
  "Adds new brand"
  [brand]
  (jdbc/insert! db-conn :brands brand))

(defn update-brand
  "Updates fields of existing brand by its id"
  [brand brand-id]
  (jdbc/update! db-conn :brands brand ["id = ?" brand-id]))

(defn delete-brand
  "Deletes brand by id"
  [brand-id]
  (jdbc/delete! db-conn :brands ["id = ?" brand-id]))

