(ns timweb-api.template.partials
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.core :refer [html]]
            [timweb-api.template.styles :as styles]))

(defn header []
  (html [:header "asdasd"]))

(defn application [title & content]
  (html5 [:head
          [:title title]
          (include-css "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css")
          (include-css "assets/styles.css")
          [:body
           (header)
           [:div content]]]))