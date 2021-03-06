(defproject timweb-api-clojure "0.1.0-SNAPSHOT"
  :description "TIM API experimental implementation in Clojure"
  :main timweb-api.core
  :aot [timweb-api.core]
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.1"]
                 [ring/ring-jetty-adapter "1.8.1"]
                 [ring/ring-json "0.5.0"]
                 [metosin/reitit-core "0.5.5"]
                 [metosin/reitit-ring "0.5.5"]
                 [metosin/reitit-swagger "0.5.5"]
                 [metosin/reitit-swagger-ui "0.5.5"]
                 [metosin/reitit-malli "0.5.5"]
                 [metosin/reitit-middleware "0.5.5"]
                 [metosin/spec-tools "0.10.3"]
                 [metosin/muuntaja "0.6.7"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.postgresql/postgresql "42.2.14"]
                 [hikari-cp "2.12.0"]
                 [honeysql "1.0.444"]
                 [aero "1.1.6"]
                 [mount "0.1.16"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [buddy/buddy-core "1.6.0"]
                 [buddy/buddy-auth "2.2.0"]
                 [buddy/buddy-sign "3.1.0"]
                 [buddy/buddy-hashers "1.4.0"]
                 [metosin/ring-http-response "0.9.1"]
                 [tick "0.4.26-alpha"]
                 [com.fasterxml.jackson.core/jackson-core "2.11.1"]
                 [hiccup "1.0.5"]
                 [garden "1.3.10"]]
  :repl-options {:init-ns timweb-api.core})
