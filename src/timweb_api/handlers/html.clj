(ns timweb-api.handlers.html
  (:require [timweb-api.templates.pages :as template]))

(def http-header {"Content-Type" "text/html; charset=UTF-8"})

(defn index
  [_]
  {:status 200
   :headers http-header
   :body (template/index)})

(defn brands
  [_]
  {:status 200
   :headers http-header
   :body (template/brands)})