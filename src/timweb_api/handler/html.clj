(ns timweb-api.handler.html
  (:require [timweb-api.template.index :as template]))

(def http-header {"Content-Type" "text/html; charset=UTF-8"})

(defn index
  [_]
  {:status 200
   :headers http-header
   :body (template/render)})