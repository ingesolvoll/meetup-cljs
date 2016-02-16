(ns meetup.forms
  (:require [reagent.ratom :refer-macros [reaction]]
            [clojure.string :as str]))

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

(defn validate-field [validations [key value]]
  (let [validation-fn (key validations)]
    (validation-fn value)))

(defn success-count [state validations]
  (->> state
       (map (partial validate-field validations))
       (filter true?)
       count))

(defn automatic-field [state index key]
  ^{:key index}
  [input-field (name key) key state])

(defn completion-as-percentage [state validations]
  (-> (success-count @state validations)
      (/ (count @state))
      (* 100)
      (floor)))

(defn progress [state validations]
  [:div.progress
   (let [percent (str (completion-as-percentage state validations) "%")]
     [:div.progress-bar {:class         (if (= percent "100%")
                                          :progress-bar-success
                                          :progress-bar-warning)
                         :role          :progress-bar
                         :aria-valuenow percent
                         :aria-valuemin "0"
                         :aria-valuemax "100"
                         :style         {:width percent}} percent])])

(defn registration [state]
  [:div.form-group
   (map-indexed (partial automatic-field state) (keys @state))])