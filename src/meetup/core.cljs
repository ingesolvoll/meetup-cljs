(ns meetup.core
  (:require [reagent.core :as r]
            [cljsjs.bootstrap]
            [meetup.slides :refer [slides]]))

(enable-console-print!)

(defonce slides-index (r/atom nil))

(defn slides-changed [e]
  (reset! slides-index (.-indexh e)))

(defn init []
  (let [prev-slide @slides-index]
    (.initialize js/Reveal)
    (when prev-slide
      (.slide js/Reveal prev-slide)))

  (.addEventListener js/Reveal "slidechanged" slides-changed))

(r/render [slides]
          (. js/document (getElementById "app")) init)