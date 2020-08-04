(ns timweb-api.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as r]
            [timweb-api.router :as router]
            [timweb-api.cache :as cache]
            [timweb-api.styles.core :as styles]
            [mount.core :as mount]
            [timweb-api.config :refer [server-config]])
  (:gen-class))

;; Atom for storing jetty server handler
(defonce server-handler (atom nil))

(def app
  "Web app"
  (r/ring-handler
    router/router
    (r/create-default-handler)))


(defn stop-server
  "Helper function to stop the server when the component's stop function is called"
  []
  (when @server-handler
    ((.stop @server-handler)
     (reset! server-handler nil))))

(defn- start-server
  "Starts ring web server"
  []
  (mount/start)
  (cache/update-all-caches)
  (styles/compile-styles)
  (let [port (:port server-config)
        handler (run-jetty app {:join? false :port port})]
    (println (format "Started web server on port %s" port))
    ;; Saving server handler to the atom to be able to close it
    (reset! server-handler handler)))

(defn -main
  "Starts the program"
  [& args]
  (start-server))
