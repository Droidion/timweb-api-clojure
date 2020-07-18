(ns timweb-api.crypto
  (:require [buddy.hashers :as hashers]))

(defn hash-password [password]
  "Hashes the password to bcrypt+sha512"
  (hashers/derive password))

(defn check-password [hashed-password plain-password]
  "Checks the password"
  (hashers/check plain-password hashed-password))