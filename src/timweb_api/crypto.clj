(ns timweb-api.crypto
  (:require [buddy.hashers :as hashers]
            [tick.alpha.api :as t]
            [buddy.sign.jwt :as jwt]))

(def secret "mysupersecret")

(defn hash-password [password]
  "Hashes the password to bcrypt+sha512"
  (hashers/derive password))

(defn check-password [hashed-password plain-password]
  "Checks the password"
  (hashers/check plain-password hashed-password))

(defn generate-token [login]
  "Generates new stateless token for user"
  (let [claims {:user (keyword login)
                :exp (t/+ (t/now) (t/new-duration 3000 :seconds))}]
    (jwt/sign claims secret {:alg :hs512})))