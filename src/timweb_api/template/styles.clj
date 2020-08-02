(ns timweb-api.template.styles
  (:require [garden.core :refer [css]]))

(def target-css-name "resources/public/styles.css")

(def menu
  [:.menu [:ul {:display "flex"}
           [:li {:margin "0 1rem"}]]])

(def logo
  [:div.logo {:display "flex"}
   [:img {:margin "0 1rem 0 1rem"}]
   [:.title {:position "relative"
             :top "2px"}]])

(def header
  [:header {:position "sticky"
            :background-color "white"
            :top "0"
            :display "flex"
            :align-items "center"
            :justify-content "space-between"
            :height "4rem"
            :width "100%"}
   logo
   menu])



(defn compile-styles
  "Compiles all styles using garden into a single css file"
  []
  (css {:output-to target-css-name} (conj [] header)))