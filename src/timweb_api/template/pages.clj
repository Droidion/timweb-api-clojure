(ns timweb-api.template.pages
  (:require [timweb-api.db.brand :as db]
            [timweb-api.template.partials :as partials]
            [hiccup.core :refer [html]]))

(defn render []
  (partials/application "Brands" (html [:table.table
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