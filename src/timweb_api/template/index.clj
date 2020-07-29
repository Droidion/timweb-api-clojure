(ns timweb-api.template.index
  (:use [hiccup.page :only (html5 include-css include-js)]
        [hiccup.core :only (html)])
  (:require [timweb-api.db.brand :as db]
            [garden.core :refer [css]]))

(defn application [title & content]
  (html5 [:head
          [:title title]
          (include-css "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css")
          [:body
           [:div content ]]]))

(defn render []
  (application "Brands" (html [:table.table
                               [:thead
                                [:tr
                                 [:th {:scope "col"} "Id"]
                                 [:th {:scope "col"} "Name"]
                                 [:th {:scope "col"} "Название"]
                                 [:th {:scope "col"} "Logo"]]]
                               [:tbody
                                (for [brand (db/get-brands)]
                                  [:tr
                                   [:th {:scope "row"} (:id brand)]
                                   [:td (:name_en brand)]
                                   [:td (:name_ru brand)]
                                   [:td (:logo brand)]])]])))