(ns timweb-api.styles.core
  (:require [garden.core :refer [css]]))

(def target-css-name "resources/public/styles.css")

(def black "#111")
(def lighter-black "#37352F")
(def pale-orange "rgba(246, 154, 19, .1)")

(def column-center-flex
  {:display "flex"
   :flex-direction "column"
   :align-items "center"
   :justify-content "center"})

(def body
  [:body (merge {:font-family "'Inter', sans-serif"
                 :color black
                 :font-size "16px"}
                column-center-flex)])

(def main
  [:main (merge {:width "100%"
                 :max-width "1300px"}
                column-center-flex)])

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

(def tim-essence
  [:img.tim-essence {:margin "4rem auto"}])

(def products-showcase
  [:div.products-showcase (merge {:background-color pale-orange
                                  :padding "2rem"
                                  :width "100%"
                                  :margin-top "2rem"
                                  :border-radius "4px"}
                                 column-center-flex)
   [:img {:width "100%"
          :max-width "700px"}]
   [:div.switcher {:display "flex"
                   :align-items "center"
                   :margin-bottom "1rem"}
    [:div.switcher-el {:display "flex"
                       :padding ".6rem 1rem"
                       :border-radius "4px"
                       :align-items "center"
                       :margin "0 .2rem"}
     [:&:hover {:background-color "rgb(238, 238, 237)"
                :cursor "pointer"}]
     [:img {:height "24px"
            :margin-right "1rem"}]
     [:span {:font-weight "500"
             :white-space "nowrap"}]]]])

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
  (css {:output-to target-css-name} (conj []
                                          body
                                          main
                                          h1
                                          h2
                                          tim-essence
                                          p
                                          header
                                          products-showcase)))