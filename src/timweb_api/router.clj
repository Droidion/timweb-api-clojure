(ns timweb-api.router
  "Routes"
  (:require [reitit.ring :as r]
            [reitit.coercion.malli]
            [reitit.ring.coercion :as coercion]
            [reitit.swagger :as swagger]
            [reitit.swagger-ui :as swagger-ui]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.parameters :as parameters]
            [timweb-api.handler.api.brand :as handler-brand]
            [timweb-api.handler.api.status :as handler-status]
            [timweb-api.handler.api.user :as handler-user]
            [timweb-api.handler.html :as handler-html]
            [timweb-api.middleware :as mw]
            [timweb-api.specs :refer [Brand AuthHeader Status]]
            [malli.util :as mu]
            [muuntaja.core :as m]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(def router
  (r/router
    [["/api"
      ["/brands" {:get {:summary    "Get list of all brands"
                       :parameters {:headers AuthHeader}
                       :responses  {200 {:description "List of all brands"
                                         :body        [:vector Brand]}
                                    401 {:description "Token was invalid"}}
                       :handler    handler-brand/get-brands
                       :middleware [mw/token-auth mw/auth]}
                 :put {:summary    "Add new brand"
                       :parameters {:headers AuthHeader
                                    :body    Brand}
                       :responses  {200 {:description "Brand was added"
                                         :body        Brand}
                                    401 {:description "Token was invalid"}}
                       :handler    handler-brand/add-brand
                       :middleware [mw/token-auth mw/auth]}}]
      ["/brands/:brand-id" {:post   {:summary    "Update existing brand"
                                    :parameters {:headers AuthHeader
                                                 :path    [:map [:brand-id int?]]
                                                 :body    Brand}
                                    :responses  {200 {:description "Brand was updated"}
                                                 401 {:description "Token was invalid"}}
                                    :handler    handler-brand/update-brand
                                    :middleware [mw/token-auth mw/auth]}
                           :delete {:summary    "Delete existing brand"
                                    :parameters {:headers AuthHeader
                                                 :path    [:map [:brand-id int?]]}
                                    :responses  {200 {:description "Brand was deleted"}
                                                 401 {:description "Token was invalid"}}
                                    :handler    handler-brand/delete-brand
                                    :middleware [mw/token-auth mw/auth]}}]
      ["/status" {:get {:summary    "Cached statistics"
                        :parameters {:headers AuthHeader}
                        :responses  {200 {:description "Statistics values"
                                          :body        Status}
                                     401 {:description "Token was invalid"}}
                        :handler    handler-status/get-status
                        :middleware [mw/token-auth mw/auth]}}]
      ["/login" {:post {:summary    "Try to log in into system with login and password to obtain session token"
                        :parameters {:body [:map [:login string?] [:password string?]]}
                        :responses  {200 {:description "User authorized, token generated"
                                          :body        [:map [:token string?]]}
                                     401 {:description "Combination of login and password could not be authenticated"}}
                        :handler    handler-user/login}}]]
     ["" {:no-doc true}
      ["/" {:get {:handler handler-html/index}}]
      ["/assets/*" (r/create-resource-handler)]
      ["/swagger.json" {:get (swagger/create-swagger-handler)}]
      ["/api-docs/*" {:get (swagger-ui/create-swagger-ui-handler)}]]]
    {:data {:coercion   (reitit.coercion.malli/create
                          {;; set of keys to include in error messages
                           :error-keys #{#_:type :coercion :in :schema :value :errors :humanized #_:transformed}
                           ;; schema identity function (default: close all map schemas)
                           :compile mu/closed-schema
                           ;; strip-extra-keys (effects only predefined transformers)
                           :strip-extra-keys true
                           ;; add/set default values
                           :default-values true
                           ;; malli options
                           :options nil})
            :muuntaja m/instance
            :middleware [swagger/swagger-feature
                         ;; query-params & form-params
                         parameters/parameters-middleware
                         ;; content-negotiation
                         muuntaja/format-negotiate-middleware
                         ;; encoding response body
                         muuntaja/format-response-middleware
                         ;; exception handling
                         exception/exception-middleware
                         ;; decoding request body
                         muuntaja/format-request-middleware
                         ;; coercing response bodys
                         coercion/coerce-response-middleware
                         ;; coercing request parameters
                         coercion/coerce-request-middleware]}}))