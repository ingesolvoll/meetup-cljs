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