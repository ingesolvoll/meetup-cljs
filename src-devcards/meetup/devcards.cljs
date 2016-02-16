(ns meetup.devcards
  (:require [meetup.forms :as forms]
            [reagent.core :as r])
  (:require-macros
    [devcards.core :refer [defcard defcard-doc defcard-rg]]))

(enable-console-print!)

(defonce fields-state (r/atom {:first-name "" :last-name ""
                               :city       "" :zip "" :mobile ""}))

(def validations {:first-name forms/valid-integer?
                  :last-name  forms/required
                  :address    forms/required
                  :city       forms/required
                  :zip        forms/valid-integer?
                  :mobile     forms/valid-integer?})

(defcard-rg form-tester
            (fn [state _] [:div
                           [forms/progress state validations]
                           [:div
                            [forms/registration state]]])
            fields-state
            {:inspect-data true :history true})