
(ns example.component.toggle-button
  (:require [quamolit.alias :refer [create-comp rect text group]]
            [quamolit.component.debug :refer [comp-debug]]
            [hsl.core :refer [hsl]]
            [quamolit.render.element :refer [translate rotate]]
            [quamolit.util.iterate :refer [iterate-instant tween]]))

(defn init-state [] true)

(defn update-state [state] (not state))

(defn init-instant [args state]
  {:value (if state 1 0) :velocity 0})

(defn on-tick [instant tick elapsed]
  (iterate-instant instant :value :velocity elapsed [0 1]))

(defn on-update [instant old-args args old-state state]
  (if (not= old-state state)
    (assoc instant :velocity (if state 0.003 -0.003))
    instant))

(defn on-unmount [instant tick]
  instant)

(defn remove? [instant] true)

(defn handle-click [mutate]
  (fn [event dispatch]
    (mutate)))

(defn render []
  (fn [state mutate]
    (fn [instant tick]
      (group {}
        (rotate {:style {:angle (* 40 (:value instant))}}
          (translate {:style {:x (* 40 (:value instant))}}
            (rect {:style {:fill-style (hsl (tween [360 240] [0 1] (:value instant)) 80 80)}
                 :event {:click (handle-click mutate)}}))
            (comment (comp-debug instant {})))))))

(def comp-toggle-button (create-comp :toggle-button init-state update-state
  init-instant on-tick on-update on-unmount remove?
  render))
