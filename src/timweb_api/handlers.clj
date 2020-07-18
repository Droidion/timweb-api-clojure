(ns timweb-api.handlers
  "Ring handlers"
  (:require [timweb-api.db :as db]
            [ring.util.response :refer [response bad-request]]))

(defn handler-brand
  "GET request for all brands"
  [_]
  (response (vec (db/all-brands))))

(defn handler-login
  "POST request for user trying to authenticate"
  [{body :body}]
  (let [login (get body :login)
        password (get body :password)
        valid? (some-> (db/user-by-login login)
                       (first)
                       (:password)
                       (= password))]
    (if valid?
      (response {:token "therewillbetoken"})
      (bad-request {:status "user not found"}))))
