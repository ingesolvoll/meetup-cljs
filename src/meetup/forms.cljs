(ns meetup.forms
  (:require [reagent.core :as r]
            [reagent.ratom :refer-macros [reaction]]
            [clojure.string :as str]))

(defonce form-state (r/atom {:first-name "" :last-name "" :address "" :city "" :zip "" :mobile ""}))


(defn input-field [label key state]
  [:div
   [:label label]
   [:input.form-control {:type  :text
                         :value (key @state)
                         :on-change
                                #(swap! state assoc
                                        key (-> % .-target .-value))}]])

(defn floor [n]
  (.floor js/Math n))

(defn required [input]
  (not (str/blank? input)))

(defn valid-integer? [input]
  (not (js/isNaN (js/parseInt input))))

(def validations {:first-name required
                  :last-name  required
                  :address    required
                  :city       required
                  :zip        valid-integer?
                  :mobile     valid-integer?})

(defn validate-field [[key value]]
  (let [validation-fn (key validations)]
    (validation-fn value)))

(def validation-results (reaction
                          (map validate-field @form-state)))

(def success-number (reaction
                      (->> @validation-results
                           (filter true?)
                           count)))

(defn automatic-field [index key]
  ^{:key index}
  [input-field (name key) key form-state])

(defn registration []
  [:div
   [:div "Completion: " (-> @success-number
                            (/ (count @form-state))
                            (* 100)
                            (floor)) "%"]
   (map-indexed automatic-field (keys @form-state))
   ])