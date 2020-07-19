(ns timweb-api.specs
  (:require [clojure.spec.alpha :as s]))

;; Generic types
(s/def :gen/id number?)
(s/def :gen/name string?)
(s/def :gen/path string?)

;; Brands
(s/def :brand/id :gen/id)
(s/def :brand/first_name :gen/name)
(s/def :brand/last_name :gen/name)
(s/def :brand/logo :gen/path)
(s/def :brand/brand (s/keys :req [:brand/id :brand/first_name :brand/last_name :brand/logo]))