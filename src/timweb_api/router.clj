(ns timweb-api.router
  "Routes"
  (:require [reitit.ring :as r]
            [timweb-api.handlers :as handlers]
            [ring.middleware.json :refer [wrap-json-response]]))

(def router
  (r/router
    ["/brand" {:get handlers/handler-brand}]
    {:data {:middleware [wrap-json-response]}}))