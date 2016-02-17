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

(defn required [value]
  (not (str/blank? value)))

(defn validate-int [value]
  (-> value
      js/parseInt
      js/isNaN
      not))

(defn text-input [label key state]
  [:div
   [:label label]
   [:input.form-control {:type  :text
                         :value (key @state)
                         :on-change
                                #(swap! state assoc
                                        key (-> % .-target .-value))}]])
