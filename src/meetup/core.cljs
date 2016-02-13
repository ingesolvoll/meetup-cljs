(ns meetup.core
  (:require [reagent.core :as r]
            [cljsjs.bootstrap]
            [meetup.slides :refer [slides]]))

(enable-console-print!)

(defonce slides-state (r/atom nil))

(defn slides-changed [e]
  (reset! slides-state (.-indexh e)))

(defn init []
  (let [prev-slide @slides-state]
    (.initialize js/Reveal (clj->js {:history true}))
    (when prev-slide
      (.slide js/Reveal prev-slide)))

  (.addEventListener js/Reveal "slidechanged" slides-changed))

(r/render [slides]
          (. js/document (getElementById "app")) init)
