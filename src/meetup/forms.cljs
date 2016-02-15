(ns meetup.forms
  (:require [reagent.core :as r]
            [reagent.ratom :refer-macros [reaction]]))

(def form-state (r/atom {:first-name "" :last-name ""}))

(def rules [#(= (:first-name %) "Inge")
            #(= (:last-name %) "Solvoll")
            #(= (:city %) "Bartebyen")
            #(-> % :zip js/parseInt js/isNaN not)])

(defn validation-results [form-state]
  (map (fn [validator] (validator form-state)) rules))

(def valid-count (reaction
                   (->> (validation-results @form-state)
                        (filter true?)
                        count)))

(def progress (reaction (* 100 (/ @valid-count 4))))

(defn progress-bar [progress]
  (let [percent (str progress "%")]
    [:div {:class (if (< progress 100) "error")} "Du er " percent " ferdig"]))

(defn names []
  [:div
   [:div [progress-bar @progress]]
   [:div.row
    [:div.col-md-4
     [:label "Fornavn"]
     [:input.form-control {:type      :text
                           :value     (:first-name @form-state)
                           :on-change #(swap! form-state assoc :first-name (-> % .-target .-value))}]]
    [:div.col-md-4
     [:label "Etternavn"]
     [:input.form-control {:type      :text
                           :value     (:last-name @form-state)
                           :on-change #(swap! form-state assoc :last-name (-> % .-target .-value))}]]

    ]
   [:div.row
    [:div.col-md-4
     [:label "By"]
     [:input.form-control {:type      :text
                           :value     (:city @form-state)
                           :on-change #(swap! form-state assoc :city (-> % .-target .-value))}]]
    [:div.col-md-4
     [:label "Postnummer"]
     [:input.form-control {:type      :text
                           :value     (:zip @form-state)
                           :on-change #(swap! form-state assoc :zip (-> % .-target .-value))}]]

    ]])