(ns timweb-api.router
  "Routes"
  (:require [reitit.ring :as r]
            [reitit.coercion.malli]
            [reitit.ring.coercion :as coercion]
            [reitit.swagger :as swagger]
            [reitit.swagger-ui :as swagger-ui]
            [timweb-api.handlers :as handlers]
            [timweb-api.middleware :as mw]
            [timweb-api.specs :refer [Brand]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(def router
  (r/router
    [["/api"
      ["/brand" {:get {:summary "Get list of all brands"
                       :parameters {:header [:map [:Authorization string?]]}
                       :responses {200 {:body [:vector Brand]}
                                   401 {:description "Token was invalid"}}
                       :handler handlers/handler-brand
                       :middleware [mw/token-auth mw/auth]}}]
      ["/login" {:post {:summary "Try to log in into system with login and password to obtain session token"
                        :parameters {:body [:map [:login string?] [:password string?]]}
                        :responses {200 {:description "User authorized, token generated"
                                         :body [:map [:token string?]]}
                                    401 {:description "Combination of login and password could not be authenticated"}}
                        :handler handlers/handler-login}}]]
     ["" {:no-doc true}
      ["/swagger.json" {:get (swagger/create-swagger-handler)}]
      ["/api-docs/*" {:get (swagger-ui/create-swagger-ui-handler)}]]]
    {:data {:coercion   reitit.coercion.malli/coercion
            :middleware [[wrap-json-body {:keywords? true :bigdecimals? true}]
                         wrap-json-response
                         coercion/coerce-exceptions-middleware
                         coercion/coerce-request-middleware
                         coercion/coerce-response-middleware]}}))