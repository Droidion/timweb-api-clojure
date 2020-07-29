(ns timweb-api.handler.api.brand
  (:require [timweb-api.db.brand :as db]
            [timweb-api.cache :as cache]
            [ring.util.http-response :refer [ok bad-request]]))

(defn get-brands
  "GET request for all brands"
  [_]
  (ok (vec (db/get-brands))))

(defn get-brands-count
  "GET Number of brands"
  [_]
  (ok {:count (:brands-count @cache/status)}))

;; returns full brand with id
(defn add-brand
  [{body :body-params}]
  (let [new-brand (first (db/add-brand body))]
    (cache/update-brands-count)
    (ok new-brand)))

(defn update-brand
  [{body :body-params path :path-params}]
  (let [number-of-updated-rows (->> :brand-id
                                    (get path)
                                    (Integer/parseInt)
                                    (db/update-brand body)
                                    (first))]
    (if (> number-of-updated-rows 0)
      (ok)
      (bad-request "Not updated. Possibly wrong brand id"))))

(defn delete-brand
  [{path :path-params}]
  (let [number-of-deleted-rows (->> :brand-id
                                    (get path)
                                    (Integer/parseInt)
                                    (db/delete-brand)
                                    (first))]
    (cache/update-brands-count)
    (if (> number-of-deleted-rows 0)
      (ok)
      (bad-request "Not deleted. Possibly wrong brand id"))))