(ns timweb-api.crypto
  (:require [buddy.hashers :as hashers]
            [tick.alpha.api :as t]
            [buddy.core.nonce :as nonce]
            [buddy.auth.backends.token :refer [jws-backend]]
            [buddy.sign.jwt :as jwt]
            [timweb-api.config :refer [server-config]]))

(def secret (nonce/random-bytes 32))

(def auth-backend (jws-backend {:secret secret
                                :options {:alg :hs512}}))

(defn- get-token-expiration []
  "Time when token is supposed to expire"
  (t/+ (t/now) (t/new-duration (:token-longevity server-config) :seconds)))

(defn hash-password [password]
  "Hashes the password to bcrypt+sha512"
  (hashers/derive password))

(defn check-password [hashed-password plain-password]
  "Checks the password"
  (hashers/check plain-password hashed-password))

(defn generate-token [login]
  "Generates new stateless token for user"
  (let [claims {:user (keyword login)
                :exp (get-token-expiration)}]
    (jwt/sign claims secret {:alg :hs512})))