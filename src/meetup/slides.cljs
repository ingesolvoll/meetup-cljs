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
    [:h1 "LISP"]
    [:p.fragment "Java virtual machine"]
    [:p.fragment "ClojureScript"]
    [:p.fragment "Leiningen"]]

   [:section
    [:h1 "LISP 1-2-3"]
    [:p "Kode er data, hva betyr det?"]]

   [:section
    [:h1 "Datastrukturer"]
    [:p.fragment "'(1 2 3 4 5)"]
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
    (+ (rand-int x) (rand-int y)))"]]]

   [:section
    [:h3 "Ei liste er et funksjonskall"]
    [:pre [:code "(split-string \",\" \"testing,testing\")"]]]

   [:section
    [:h1 "Fullskala eksempel"]
    [:pre [:code "(->> \"1 4 ::2,3,4,5  8\"
    (re-seq #\"w+\")
    (map js/parseInt)
    (filter odd?)
    (apply +))



    ; => The answer is 9"]]]

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
    [:div {:class \"commentAuthor\"}
      author]])"
      ]]]

   [:section
    [:h1 "Hiccup syntax"]
    [:pre.fragment
     [:code.clojure
      "[:tagname.styleclass {:attr-key \"attr-value\"} child-content]"
      ]]
    [:pre.fragment
     [:code.clojure
      "[:div.form-group
  [:input {:type \"text\" :value \"Hello\"}]]"
      ]]]

   [:section
    [:h1 "Bootstrap form"]
    [forms/names]
    ]])