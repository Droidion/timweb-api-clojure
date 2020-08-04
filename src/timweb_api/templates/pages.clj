(ns timweb-api.templates.pages
  (:require [timweb-api.db.brand :as db]
            [timweb-api.templates.partials :as partials]
            [hiccup.core :refer [html]]))

(defn index []
  (partials/application "TIM Group" (html [:img.tim-essence {:src "assets/img/timgroup-essence.png"}]
                                          [:h1 "Деловые игры и тренинги"]
                                          [:p.caption "Финансы. Переговоры. Компьютерное моделирование."]
                                          [:div.products-showcase
                                           [:div.switcher
                                            [:div.switcher-el
                                             [:img {:src "assets/img/factory-icon.svg"}]
                                             [:span "Игра ВИНК"]]
                                            [:div.switcher-el
                                             [:img {:src "assets/img/friends-icon.svg"}]
                                             [:span "Игра Переговоры"]]
                                            [:div.switcher-el
                                             [:img {:src "assets/img/seminar-icon.svg"}]
                                             [:span "Тренинги"]]]
                                           [:img {:src "assets/img/vink-interface.png"}]])))

(defn brands []
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