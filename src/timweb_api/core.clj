(ns timweb-api.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as r]
            [timweb-api.router :as router])
  (:gen-class))

(def app
  (r/ring-handler
    router/router
    (r/create-default-handler)))


(defn server
  "Starts ring web server"
  []
  (println "Starting web server on port 8080")
  (run-jetty app {:port 8080}))

(defn -main
  "Starts the program"
  [& args]
  (server))
