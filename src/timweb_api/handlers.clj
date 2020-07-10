(ns timweb-api.handlers
  "Ring handlers"
  (:require [timweb-api.db :as db]
            [ring.util.response :refer [response]]))

(defn handler-brand
  "Handles GET request for all brands"
  [_]
  (response (vec (db/all-brands))))