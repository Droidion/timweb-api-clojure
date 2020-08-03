(ns timweb-api.template.styles
  (:require [garden.core :refer [css]]))

(def target-css-name "resources/public/styles.css")

(def black "#111")
(def lighter-black "#37352F")

(def body
  [:body {:font-family "'Inter', sans-serif"
          :color black
          :font-size "16px"
          :display "flex"
          :flex-direction "column"
          :align-items "center"
          :justify-content "center"}])

(def main
  [:main {:width "100%"
          :max-width "1300px"}])

(def h1
  [:h1 {:font-size "4.6rem"
        :font-weight "700"
        :text-align "center"}])

(def h2
  [:h2 {:font-size "2.4rem"
        :font-weight "700"}])

(def p
  [:p [:&.caption {:font-size "1.25rem"
                   :opacity ".7"
                   :text-align "center"}]])

(def menu
  [:.menu [:ul {:display "flex"}
           [:li {:margin "0 .8rem"
                 :color lighter-black
                 :border-radius ".2rem"
                 :padding ".2rem .5rem .3rem .5rem"
                 :font-size ".9rem"
                 :font-weight "500"}
            [:&:hover {:background-color "rgb(238, 238, 237)"
                       :cursor "pointer"}]]]])

(def logo
  [:div.logo {:display "flex"}
   [:img {:margin "0 1rem 0 1rem"}]
   [:.title {:position "relative"
             :color lighter-black
             :font-weight "500"
             :top "2px"}]])

(def header
  [:header {:position "sticky"
            :background-color "white"
            :top "0"
            :display "flex"
            :align-items "center"
            :justify-content "space-between"
            :height "4rem"
            :max-width "1300px"
            :width "100%"}
   logo
   menu])



(defn compile-styles
  "Compiles all styles using garden into a single css file"
  []
  (css {:output-to target-css-name} (conj [] body main h1 h2 p header)))