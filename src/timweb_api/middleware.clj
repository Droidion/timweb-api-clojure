(ns timweb-api.middleware
  (:require [buddy.auth :refer [authenticated?]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [timweb-api.crypto :as crypto]
            [ring.util.http-response :refer [unauthorized]]))

(defn auth
  "Middleware used in routes that require authentication. If request is not
   authenticated a 401 not authorized response will be returned"
  [handler]
  (fn [request]
    (if (authenticated? request)
      (handler request)
      (unauthorized))))

(defn token-auth
  "Middleware used on routes requiring token authentication"
  [handler]
  (wrap-authentication handler crypto/auth-backend))