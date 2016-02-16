(ns meetup.devcards
  (:require [meetup.forms :as forms]
            [reagent.core :as r])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-doc defcard-rg]]))

(enable-console-print!)

(defonce fields-state (r/atom {:first-name "" :last-name ""
                               :city       "" :zip "" :mobile ""}))

(def validations {:first-name forms/valid-integer?
                  :last-name  forms/required
                  :city       forms/required
                  :zip        forms/valid-integer?
                  :mobile     forms/valid-integer?})

(defcard well-hello-there
         "# Devcards
         - Can do many different things
         - Supports markdown
         - Gives you instant feedback
         - But this time, with multiple states")

(defcard some-documentation-is-required
         "#I made this nice component
         This is how you set up validations for it"
         (dc/mkdn-pprint-source validations))

(defcard state-should-be-a-map
         "#Guide to state
         - You state input should be a map"
         (dc/mkdn-pprint-source fields-state))

(defcard-rg lets-test-the-form
            (fn [state _] [:div
                           [forms/progress state validations]
                           [forms/registration state validations]])
            fields-state
            {:history true :inspect-data true})

(defcard-rg lets-go-crazy
            (let [huge-validations (->> (range 100)
                                        (map (fn [i] [(keyword (str "field-" i)) forms/required]))
                                        (into (sorted-map)))]
              (fn [state _] [:div
                             [forms/progress state huge-validations]
                             [forms/registration state huge-validations]]))
            (r/atom (->> (range 100)
                         (mapv (fn [i] [(keyword (str "field-" i)) ""]))
                         (into (sorted-map))))
            {:history true :inspect-data true}
            )