(ns timweb-api.cache
  "Cache using atoms"
  (:require [timweb-api.db.brand :as db]))

(def status (atom {:brands-count 0}))

(defn update-brands-count
  "Updates atom with the number of brands in the DB"
  []
  (let [count (-> (db/get-brands-count)
                  (first)
                  (get :count))]
    (swap! status assoc :brands-count count)))

(defn update-all-caches
  "Updates all cache atoms"
  []
  (update-brands-count))