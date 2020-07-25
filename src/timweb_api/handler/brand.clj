(ns timweb-api.handler.brand
  (:require [timweb-api.db.brand :as db]
            [ring.util.http-response :refer [ok bad-request]]))

(defn get-brands
  "GET request for all brands"
  [_]
  (ok (vec (db/get-brands))))

;; returns full brand with id
(defn add-brand
  [{body :body-params}]
  (ok (first (db/add-brand body))))

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
    (if (> number-of-deleted-rows 0)
      (ok)
      (bad-request "Not deleted. Possibly wrong brand id"))))