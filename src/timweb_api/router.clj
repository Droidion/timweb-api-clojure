(ns timweb-api.router
  "Routes"
  (:require [reitit.ring :as r]
            [clojure.spec.alpha :as s]
            [reitit.coercion.spec]
            [reitit.ring.coercion :as coercion]
            [reitit.swagger :as swagger]
            [reitit.swagger-ui :as swagger-ui]
            [timweb-api.handlers :as handlers]
            [timweb-api.middleware :as mw]
            [timweb-api.specs]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(def router
  (r/router
    [["/api"
      ["/brand" {:get {:summary "Get list of all brands"
                       :parameters {:header {:Authorization string?}}
                       :responses {200 {:body (s/coll-of :brand/brand)}
                                   401 {:description "Token was invalid"}}
                       :handler handlers/handler-brand
                       :middleware [mw/token-auth mw/auth]}}]
      ["/login" {:post {:summary "Try to log in into system with login and password to obtain session token"
                        :parameters {:body {:login string? :password string?}}
                        :responses {200 {:description "User authorized, token generated"
                                         :body {:token string?}}
                                    401 {:description "Combination of login and password could not be authenticated"}}
                        :handler handlers/handler-login}}]]
     ["" {:no-doc true}
      ["/swagger.json" {:get (swagger/create-swagger-handler)}]
      ["/api-docs/*" {:get (swagger-ui/create-swagger-ui-handler)}]]]
    {:data {:coercion   reitit.coercion.spec/coercion
            :middleware [[wrap-json-body {:keywords? true :bigdecimals? true}]
                         wrap-json-response
                         coercion/coerce-exceptions-middleware
                         coercion/coerce-request-middleware
                         coercion/coerce-response-middleware]}}))