(ns timweb-api.handler.brand
  (:require [timweb-api.db.brand :as db]
            [ring.util.http-response :refer [ok bad-request]]))

(defn get-all
  "GET request for all brands"
  [_]
  (ok (vec (db/get-all))))

(defn add
  [{body :body-params}]
  (ok (first (db/add body))))

(defn update
  [brand brand-id]
  (let [number-of-updated-rows (db/update brand brand-id)]
    (if (> number-of-updated-rows 0)
      (ok)
      (bad-request "Not updated. Possibly wrong brand id"))))

(defn delete
  [brand-id]
  (let [number-of-deleted-rows (db/delete brand-id)]
    (if (> number-of-deleted-rows 0)
      (ok)
      (bad-request "Not deleted. Possibly wrong brand id"))))