(ns timweb-api.template.styles
  (:require [garden.core :refer [css]]))

(def target-css-name "resources/public/styles.css")

(def header
  [:header {:background-color "red"
            :width "200px"
            :height "100px"}])

(defn compile-styles
  "Compiles all styles using garden into a single css file"
  []
  (css {:output-to target-css-name} header))