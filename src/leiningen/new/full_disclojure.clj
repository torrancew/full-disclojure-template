(ns leiningen.new.full-disclojure
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render
  (renderer "full-disclojure"))

(defn full-disclojure
  "Generate a new Compojure+Om webapp"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' full-disclojure project.")
    (->files data
             [".gitignore" (render "gitignore" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["README.md" (render "README.md" data)]
             ["Procfile" (render "Procfile" data)]
             ["project.clj" (render "project.clj" data)]
             ["resources/public/index.html" (render "index.html" data)]
             ["env/dev/resources/public/index.html" (render "index-dev.html" data)]
             ["src/clj/{{sanitized}}/server.clj" (render "clj/server.clj" data)]
             ["test/clj/{{sanitized}}/server_test.clj" (render "clj/test.clj" data)]
             ["src/cljs/{{sanitized}}/client.cljs" (render "cljs/client.cljs" data)]
             ["test/cljs/{{sanitized}}/runner.cljs" (render "cljs/test-runner.cljs" data)]
             ["test/cljs/{{sanitized}}/client_test.cljs" (render "cljs/test.cljs" data)])))
