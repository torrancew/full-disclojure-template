(ns {{name}}.client
  (:require [om.core :as om]
            [om.dom  :as dom]
            [om-bootstrap.panel :as p]
            [om-bootstrap.input :as i]))

(defonce app-state
  (atom {}))

(defn header
  [data owner]
  (om/component
    (dom/header #js {:role "banner" :className "navbar navbar-inverse"}
                (dom/div #js {:className "container"}
                         (dom/a #js {:href "#" :className "navbar-brand"}
                                "{{name}}")))))

(defn app
  [& args]
  (om/root
    header
    app-state
    {:target (. js/document
                 (getElementById "header"))})

  (om/root
    #(om/component (dom/div nil))
    app-state
    {:target (. js/document
                 (getElementById "app"))}))

(def ^:export main app)
