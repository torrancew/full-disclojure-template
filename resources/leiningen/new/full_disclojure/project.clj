(defproject {{name}} "0.0.1-SNAPSHOT"
  :description "FIXME: write a description"
  :url "https://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [;; Backend
                 [org.clojure/clojure "1.7.0"]
                 [environ "1.0.1"]
                 [compojure "1.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 ;; Frontend
                 [org.clojure/clojurescript "1.7.189"]
                 [org.omcljs/om "1.0.0-alpha28"]
                 [racehub/om-bootstrap "0.5.3"]
                 [doo "0.1.6"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.2"]
            [lein-pdo "0.1.1"]
            [lein-doo "0.1.6"]
            [environ/environ.lein "0.3.1"]]
  :hooks [leiningen.cljsbuild environ.leiningen.hooks]
  :aliases {"up"     ["pdo" "cljsbuild" "auto" "dev," "ring" "server-headless"]
            "brepl"  ["trampoline" "cljsbuild" "repl-listen"]
            "jsrepl" ["trampoline" "cljsbuild" "repl-rhino"]}
  :ring {:handler {{name}}.server/app}
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :cljsbuild {:builds {:dev {:source-paths ["src/cljs"]
                             :compiler {:output-to "env/dev/resources/public/js/app.js"
                                        :output-dir "env/dev/resources/public/js/out"
                                        :optimizations :none
                                        :source-map true
                                        :pretty-print true}}
                       :test {:source-paths ["src/cljs" "test/cljs"]
                              :compiler {:output-to "env/test/resources/public/js/test.js"
                                         :output-dir "env/test/resources/public/js/out"
                                         :main {{name}}.runner
                                         :optimizations :whitespace
                                         :pretty-print true}}
                       :release {:source-paths ["src/cljs"]
                                 :compiler {:output-to "resources/public/js/app.js"
                                            :optimizations :advanced
                                            :pretty-print false}}}
              :test-commands {"unit" ["lein" "doo"
                                      "slimer" "test" "once"]}}
  :uberjar-name "{{name}}-standalone.jar"
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]
         :resource-paths ["env/dev/resources"]}
   :test {:resource-paths ["env/test/resources"]}
   :production {:env {:production true}}
   :uberjar {:aot :all}}
  :target-path "target/%s"
  :clean-targets ^{:protect false} ["target"
                                    "resources/public/js"
                                    "env/dev/resources/public/js"
                                    "env/test/resources/public/js" ])
