(ns timweb-api.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as r]
            [timweb-api.router :as router]
            [mount.core :as mount]
            [timweb-api.config :refer [server-config]])
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
  (let [port (:port server-config)]
    (println (format "Starting web server on port %s" port))
    (run-jetty app {:join? false :port port})))

(defn -main
  "Starts the program"
  [& args]
  (server))
