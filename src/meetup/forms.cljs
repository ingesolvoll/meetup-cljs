(ns meetup.forms
  (:require [reagent.ratom :refer-macros [reaction]]
            [clojure.string :as str]))

(defn required [value]
  (not (str/blank? value)))

(defn validate-int [value]
  (-> value
      js/parseInt
      js/isNaN
      not))

(defn validate-field [validators [key value]]
  (if-let [validator (key validators)]
    (validator value)
    true))


(defn field [state label key]
  [:div
   [:label label]
   [:input.form-control {:type  :text
                         :value (key @state)
                         :on-change
                                #(swap! state assoc
                                        key (-> % .-target .-value))}]])

(defn auto-labeled-field [state key]
  [field state (name key) key])

(defn progress [state validators]
  [:div (let [p (->> @state
                     (map (partial validate-field validators))
                     (filter true?)
                     (count)
                     (#(/ % (count validators)))
                     (* 100)
                     (.floor js/Math))]
          [:div.progress
           (let [percent (str p "%")]
             [:div.progress-bar {:class         (if (= percent "100%")
                                                  :progress-bar-success
                                                  :progress-bar-warning)
                                 :role          :progress-bar
                                 :aria-valuenow percent
                                 :aria-valuemin "0"
                                 :aria-valuemax "100"
                                 :style         {:width percent}} percent])])])

(defn form [state]
  [:div
   (map-indexed (fn [i key]
          ^{:key i}
          [auto-labeled-field state key]) (keys @state))
   ])