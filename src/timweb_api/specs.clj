(ns timweb-api.specs)

(def Brand
  [:map
   [:id number?]
   [:name_ru string?]
   [:name_en string?]
   [:logo string?]])

(def AuthHeader
  [:map
   [:Authorization string?]])