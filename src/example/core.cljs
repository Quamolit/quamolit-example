
(ns example.core
  (:require
    [quamolit.core :refer [render-page configure-canvas setup-events]]
    [example.component.container :refer [comp-container]]
    [quamolit.util.time :refer [get-tick]]
    [devtools.core :as devtools]))

(defonce store-ref (atom 0))

(defonce states-ref (atom {}))

(defonce loop-ref (atom nil))

(defn updater [store op op-data]
  store)

(defn dispatch [op op-data]
  (reset! store-ref (updater @store-ref op op-data)))

(defn render-loop []
  (let [target (.querySelector js/document "#app")]
    (render-page (comp-container @store-ref) states-ref target)
    (reset! loop-ref (js/requestAnimationFrame render-loop))))

(defn -main []
  (enable-console-print!)
  (println "app started.")
  (devtools/install!)
  (let [target (.querySelector js/document "#app")]
    (configure-canvas target)
    (setup-events target dispatch)
    (render-loop)))

(set! (.-onload js/window) -main)

(set! (.-onresize js/window)
  (fn [event]
    (let [target (.querySelector js/document "#app")]
      (configure-canvas target))))

(defn on-jsload []
  (js/cancelAnimationFrame @loop-ref)
  (js/requestAnimationFrame render-loop)
  (println "code updated."))
