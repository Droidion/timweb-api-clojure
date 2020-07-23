(ns timweb-api.handler.brand
  (:require [timweb-api.db.brand :as brand]
            [ring.util.http-response :refer [ok]]))

(defn handler-brand
  "GET request for all brands"
  [_]
  (ok (vec (brand/all-brands))))