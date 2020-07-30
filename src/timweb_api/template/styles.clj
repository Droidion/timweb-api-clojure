(ns timweb-api.template.styles
  (:require [garden.core :refer [css]]))

(defn header []
  (css [:header {:background-color "red" :width "200px" :height "100px"}]))