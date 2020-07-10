(defproject timweb-api-clojure "0.1.0-SNAPSHOT"
  :description "TIM API experimental implementation in Clojure"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.1"]
                 [ring/ring-jetty-adapter "1.8.1"]
                 [ring/ring-json "0.5.0"]
                 [metosin/reitit-core "0.5.2"]
                 [metosin/reitit-ring "0.5.2"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.postgresql/postgresql "42.2.14.jre7"]
                 [hikari-cp "2.12.0"]
                 [honeysql "1.0.444"]
                 [aero "1.1.6"]
                 [mount "0.1.16"]]
  :repl-options {:init-ns timweb-api.core})
