(ns meetup.forms
  (:require [reagent.ratom :refer-macros [reaction]]
            [clojure.string :as str]))

(defn form [state]
  [:div
   [:div
    [:label "Fornavn"]
    [:input.form-control {:type  :text
                          :value (:first-name @state)
                          :on-change
                                 #(swap! state assoc
                                         :first-name (-> % .-target .-value))}]]
   [:div
    [:label "Etternavn"]
    [:input.form-control {:type  :text
                          :value (:last-name @state)
                          :on-change
                                 #(swap! state assoc
                                         :first-name (-> % .-target .-value))}]]])