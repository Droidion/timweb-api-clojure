(ns timweb-api.handler.user
  (:require [timweb-api.db.user :as user]
            [ring.util.http-response :refer [ok unauthorized]]
            [timweb-api.crypto :as crypto]))

(defn handler-login
  "POST request for user trying to authenticate"
  [{body :body-params}]
  (let [login (get body :login)
        password (get body :password)
        valid? (some-> (user/user-by-login login)
                       (first)
                       (:password)
                       (crypto/check-password password))]
    (if valid?
      (ok {:token (crypto/generate-token login)})
      (unauthorized))))