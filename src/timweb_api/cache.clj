(ns timweb-api.cache
  "Cache using atoms"
  (:require [timweb-api.db.brand :as db]))

(def brands-count "Number of brands" (atom 0))

(defn update-brands-count
  "Updates atom with the number of brands in the DB"
  []
  (reset! brands-count (-> (db/get-brands-count)
                            (first)
                            (get :count))))

(defn update-all-caches
  "Updates all cache atoms"
  []
  (update-brands-count))