(ns meetup.devcards
  (:require [meetup.forms :as forms]
            [meetup.slides :as slides]
            [reagent.core :as r])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-doc defcard-rg]]))

(enable-console-print!)

(defonce state (r/atom {:first-name "Inge" :last-name "Solvoll" :mobile "555"}))