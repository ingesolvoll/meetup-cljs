(ns meetup.slides
  (:require [meetup.forms :as forms]))

(defn slides []
  [:div.slides

   [:section
    [:h1 "ClojureScript"]
    [:h3 "Snakk med koden din"]
    [:p
     "Inge Solvoll"]]

   [:section
    [:h2 "Clojure"]
    [:p.fragment "Opprinnelig for Java"]
    [:p.fragment "Leiningen, ikke Grunt"]
    [:p.fragment "Namespaces uten lekkasje"]
    [:p.fragment "Cons: uleselig"]]

   [:section
    [:h1 "LISP 1-2-3"]
    [:p "Kode er data, hva betyr det?"]]

   [:section
    [:h3 "Datastrukturer"]
    [:p.fragment "(1 2 3 4 5)"]
    [:p.fragment "[:vector :med :keywords]"]
    [:p.fragment "{:keys-and \"values\" :come-in :pairs}"]
    [:p.fragment "#{true false \"unique\"}"]]

   [:section
    [:h3 "\"Variabler\""]
    [:pre.fragment [:code "(def my-variable 2) ; Available globally in namespace"]]
    [:pre.fragment [:code "(let [my-variable 2]
    (+ 1 my-variable)) ; Let statement returns 3

my-variable ; Not available here, throws exception"]]]

   [:section
    [:h3 "Funksjon = lister og vektorer"]
    [:pre.fragment [:code "(fn [x y]
    (+ (rand-int x) (rand-int y)))"]]
    [:pre.fragment [:code "(defn do-something [x y]
    (+ (rand-int x) (rand-int y)))"]]
    [:pre.fragment [:code "(do-something 1 2)"]]]

   [:section
    [:h3 "Parantesbonanza"]
    [:pre [:code "(/ 2 (+ 4 (* 3 (- 5 2))))"]]
    [:p.fragment "Ingen problemer med prioritet"]
    [:p.fragment "Muuuligens noe tunglest"]]

   [:section
    [:h3 "Dypere vann"]
    [:pre [:code "(->>" [:span.fragment " \"1 4 ::2,3,4,5  8\""]
           [:span.fragment "\n     (re-seq #\"\\w+\")"]
           [:span.fragment "    ;(\"1\",\"4\",\"2\",\"3\",\"4\",\"5\",\"8\")"]
           [:span.fragment "\n     (map js/parseInt)"]
           [:span.fragment "  ;(1,4,2,3,4,5,8)"]
           [:span.fragment "\n     (filter odd?)"]
           [:span.fragment "      ;(1,3,5)"]
           [:span.fragment "\n     (apply +))"]
           [:span.fragment "         ; 9"]]]]

   [:section
    [:h1 "React"]
    [:pre
     [:code.javascript
      "var Comment = React.createClass({
      render: function() {
        return
          React.DOM.div({className: \"comment\"},
            React.DOM.h2({className \"commentAuthor\"},
            this.props.author)
        );
      }
})"]]]

   [:section
    [:h1 "React med JSX"]
    [:pre
     [:code.javascript
      "var Comment = React.createClass({
      render: function() {
        return (
          <div className=\"comment\">
            <h2 className=\"commentAuthor\">
            {this.props.author}</h2></div>
        );
      }
})"]]]

   [:section
    [:h1 "Reagent"]
    [:pre
     [:code.clojure
      "(defn Comment [author]
  [:div.comment
    [:h2 {:class \"commentAuthor\"}
      author]])"
      ]]]

   [:section
    [:h1 "Reagent"]
    [:pre
     [:code.clojure
      "(defn Comment [author]
  [:div.comment>h2.commentAuthor author])"
      ]]]

   [:section
    [:h3 "Hiccup syntax"]
    [:pre.fragment
     [:code.clojure
      "[:tagname.styleclass {:attr-key \"attr-value\"} child-content]"]]
    [:pre.fragment
     [:code.clojure
      "[:div.form-group
  [:input {:type \"text\" :value \"Hello\"}]]"
      ]]]

   [:section
    [:h3 "Atoms"]
    [:pre>code
     [:span.fragment "(def state (atom {}))"]
     [:span.fragment "\n\n(reset! state {:name \"Inge\"})"]
     [:span.fragment "\n\n(swap! state assoc :address \"Trondheim\")"]
     [:span.fragment "\n\n[:input {:type :text :value (:name @state)}]"]
     ]]

   [:section
    [:h3 "Live reload 1.0"]
    [:p [:img {:src "f5_key.png" :width "500" :height "500"}]]
    ]

   [:section
    [:h3 "Live reload: Figwheel is awesome"]
    [:p.fragment "Reloadable code"]
    [:p.fragment "Setup/teardown"]]])