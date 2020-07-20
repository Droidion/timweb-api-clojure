(ns timweb-api.handlers
  "Ring handlers"
  (:require [timweb-api.db.brand :as brand]
            [timweb-api.db.user :as user]
            [buddy.auth :refer [authenticated? throw-unauthorized]]
            [ring.util.http-response :refer [ok unauthorized]]
            [timweb-api.crypto :as crypto]))

(defn handler-brand
  "GET request for all brands"
  [_]
  (ok (vec (brand/all-brands))))

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
