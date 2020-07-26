(ns timweb-api.specs)

(def Brand
  [:map
   [:id {:optional true} number?]
   [:name_ru string?]
   [:name_en string?]
   [:logo string?]])

(def AuthHeader
  [:map
   [:Authorization string?]])

(def Status
  [:map
   [:brands-count int?]])