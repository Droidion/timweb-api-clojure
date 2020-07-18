(ns timweb-api.handlers
  "Ring handlers"
  (:require [timweb-api.db :as db]
            [buddy.auth :refer [authenticated? throw-unauthorized]]
            [ring.util.http-response :refer [ok unauthorized]]
            [timweb-api.crypto :as crypto]))

(defn handler-brand
  "GET request for all brands"
  [_]
  (ok (vec (db/all-brands))))

(defn handler-login
  "POST request for user trying to authenticate"
  [{body :body}]
  (let [login (get body :login)
        password (get body :password)
        valid? (some-> (db/user-by-login login)
                       (first)
                       (:password)
                       (crypto/check-password password))]
    (if valid?
      (ok {:token (crypto/generate-token login)})
      (unauthorized))))
