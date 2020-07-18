(ns timweb-api.router
  "Routes"
  (:require [reitit.ring :as r]
            [timweb-api.handlers :as handlers]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(def router
  (r/router
    [["/brand" {:get handlers/handler-brand}]
     ["/login" {:post handlers/handler-login}]]
    {:data {:middleware [[wrap-json-body {:keywords? true :bigdecimals? true}] wrap-json-response]}}))