(ns {{name}}.server
  (:require [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [environ.core             :refer [env]]
            [compojure.core           :refer [GET defroutes]]
            [ring.util.response       :refer [content-type response resource-response]]
            [ring.middleware.json     :refer [wrap-json-response]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.not-modified :refer [wrap-not-modified]])
  (:gen-class))

(defroutes routes
  (GET "/" []
       (-> "index.html"
           (resource-response {:root "public"})
           (content-type "text/html")))
  (route/not-found "Not Found"))

(def app
  (-> routes
      (wrap-json-response)
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)
      (wrap-defaults api-defaults)))

(defn -main
  [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty app {:port port :join? false})))
