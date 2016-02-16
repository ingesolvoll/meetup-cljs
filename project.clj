(defproject meetup "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [reagent "0.5.1"]
                 [devcards "0.2.0-8"]
                 [cljsjs/bootstrap "3.3.6-0"]]

  :plugins [[lein-figwheel "0.5.0-6"]
            [lein-cljsbuild "1.1.2" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]


  :cljsbuild {:builds
              [{:id           "devcards"
                :source-paths ["src" "src-devcards"]
                :figwheel     {:devcards true}
                :compiler     {:main                 "meetup.devcards"
                               :asset-path           "js/compiled/devcards_out"
                               :output-to            "resources/public/js/compiled/meetup_devcards.js"
                               :output-dir           "resources/public/js/compiled/devcards_out"
                               :source-map-timestamp true}}

               {:id           "dev"
                :source-paths ["src"]
                :figwheel     true
                :compiler     {:main                 meetup.core
                               :asset-path           "js/compiled/out"
                               :output-to            "resources/public/js/compiled/meetup.js"
                               :output-dir           "resources/public/js/compiled/out"
                               :source-map-timestamp true}}]}

  :figwheel {:css-dirs ["resources/public/css"]})