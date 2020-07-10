(ns timweb-api.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as r]
            [timweb-api.router :as router]
            [mount.core :as mount])
  (:gen-class))

(def app
  "Web app"
  (r/ring-handler
    router/router
    (r/create-default-handler)))


(defn- server
  "Starts ring web server"
  []
  (mount/start)
  (println "Starting web server on port 8080")
  (run-jetty app {:join? false :port 8080}))

(defn -main
  "Starts the program"
  [& args]
  (server))
