
(ns example.component.container
  (:require
    [hsl.core :refer [hsl]]
    [quamolit.alias :refer [create-comp rect]]
    [example.component.toggle-button :refer [comp-toggle-button]]))

(defn render [store]
  (fn [state mutate]
    (fn [instant tick]
      (comp-toggle-button))))

(def comp-container (create-comp :container render))
