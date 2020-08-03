(ns timweb-api.template.partials
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.core :refer [html]]
            [timweb-api.template.styles :as styles]))

(defn menu []
  (html [:nav.menu
         [:ul
          [:li "Расписание"]
          [:li "Компьютерные симуляции"]
          [:li "Семинары"]
          [:li "Статистика"]
          [:li "Клиенты"]
          [:li "Отзывы"]
          [:li "Контакты"]]]))

(defn header []
  (html [:header
         [:div.logo
          [:img {:src "assets/img/logo.svg"}]
          [:div.title "Группа ТИМ"]]
         (menu)]))

(defn application [title & content]
  (html5 [:head
          [:title title]
          (include-css "https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap")
          (include-css "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css")
          (include-css "assets/reset.css")
          (include-css "assets/styles.css")
          [:body
           (header)
           [:main content]]]))