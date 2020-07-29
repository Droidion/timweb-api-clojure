(ns timweb-api.handler.api.status
  (:require [timweb-api.cache :as cache]
            [ring.util.http-response :refer [ok]]))

(defn get-status
  "GET Number of brands"
  [_]
  (ok @cache/status))