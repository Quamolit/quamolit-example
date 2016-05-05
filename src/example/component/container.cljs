
(ns example.component.container
  (:require
    [hsl.core :refer [hsl]]
    [quamolit.alias :refer [create-comp rect]]))

(defn render [store]
  (fn [state mutate]
    (fn [instant tick]
      (rect {:style {:fill-style (hsl 200 90 80)}}))))

(def comp-container (create-comp :container render))
