(ns meetup.forms
  (:require [reagent.core :as r]
            [reagent.ratom :refer-macros [reaction]]))

(defonce form-state (r/atom {:first-name "Inge" :last-name "Solvoll"}))

(def rules [#(= (:first-name %) "Inge")
            #(= (:last-name %) "Solvoll")])

(defn validation-results [form-state]
  (map (fn [validator] (validator form-state)) rules))

(def progress (reaction
                (->> (validation-results @form-state)
                     (filter true?)
                     count
                     (#(/ % 2))
                     (* 100)
                     )))

(def valid? (reaction
              (not-any? false? (validation-results @form-state))))

(defn names []
  [:div
   [:div "Nearly done: " @progress]
   [:div.row
    [:div @valid?]
    [:div.col-md-4
     [:label "Fornavn"]
     [:input.form-control {:type      :text
                           :class     (if-not @valid? "error")
                           :value     (:first-name @form-state)
                           :on-change #(swap! form-state assoc :first-name (-> % .-target .-value))}]]
    [:div.col-md-4
     [:label "Etternavn"]
     [:input.form-control {:type      :text
                           :class     (if-not @valid? "error")
                           :value     (:last-name @form-state)
                           :on-change #(swap! form-state assoc :last-name (-> % .-target .-value))}]]

    ]])