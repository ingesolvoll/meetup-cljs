(ns meetup.devcards
  (:require [meetup.forms :as forms]
            [meetup.slides :as slides]
            [reagent.core :as r])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-doc defcard-rg]]))

(enable-console-print!)

(defonce state (r/atom {:first-name "Inge"
                        :last-name  "Solvoll"
                        :mobile     "555"}))

(def validators {:first-name forms/required
                 :last-name forms/required
                 :mobile forms/validate-int})

(defcard my-first
         "#Markdown
         - With bullets")

(defcard doc
         "#How to configure
         Here's how we structure the validators"
         (dc/mkdn-pprint-source validators))

(defcard funny
         (dc/mkdn-pprint-source +))

(defcard-rg reagent-sandbox
            [:div "Heisann fra LISP " (+ 1 2)])

(defcard-rg form-tester
            (fn [state _]
              [:div
               [forms/progress state validators]
               [forms/form state]])
            state
            {:inspect-data true :history true})

(defcard-rg progress-tester
            [:div.row
             [:div.col-md-2 [forms/progress state validators]]
             [:div.col-md-4 [forms/progress state validators]]
             [:div.col-md-6 [forms/progress state validators]]])