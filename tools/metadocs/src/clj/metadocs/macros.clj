(ns metadocs.macros
  (:require [cheshire.core :as json]
            [clj-http.client :as http]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def contribution-dir "../../models/")

(defn dir-contents [dir]
  (-> dir
      io/file
      .list))

(defn load-json [file-name]
  (json/parse-stream (io/reader file-name) true))

(defn load-contributions []
  (let [model-file-names (-> contribution-dir
                             dir-contents)]
    (map #(load-json (str contribution-dir %)) model-file-names)))

(defn delete-dir-contents [dir]
  (doseq [file (-> dir io/file .list)]
    (io/delete-file file true)))

(defn sanitize [s]
  (string/replace s #" " "_"))

(defmacro defcontributionroutes [state page]
  (let [model-file-names (-> contribution-dir
                             dir-contents)]
    (delete-dir-contents "resources/public/contributions/")
    `(do ~@(map (fn [contribution-file-name]
                  (let [contribution (-> (str contribution-dir contribution-file-name)
                                         load-json)
                        contribution-name (-> contribution :name sanitize)
                        feature-set (set (mapcat :features (:sections contribution)))]
                    (io/copy (io/file "resources/public/index.html") (io/file (str "resources/public/contributions/" contribution-name ".html")))
                    `(~'defroute ~(str "/metalib/contributions/" contribution-name ".html") []
                       (~'swap! ~state ~'assoc :current-page #(~page ~contribution)))))
                model-file-names))))

(defmacro defperspectiveroutes [state page]
  (let [models (load-contributions)
        sections (mapcat :sections models)
        perspectives (set (mapcat :perspectives sections))]
    (delete-dir-contents "resources/public/perspectives/")
    `(do ~@(map (fn [perspective]
                  (let [contributions (map :name (filter #(contains? (set (mapcat :perspectives (:sections %))) perspective) models))]
                    (io/copy (io/file "resources/public/index.html") (io/file (str "resources/public/perspectives/" (sanitize perspective) ".html")))
                    `(~'defroute ~(str "/metalib/perspectives/" (sanitize perspective) ".html") []
                      (~'swap! ~state ~'assoc :current-page #(~page ~perspective (list ~@contributions))))))
                perspectives))))

(defmacro deffeatureroutes [state page]
  (let [models (load-contributions)
        sections (mapcat :sections models)
        features (set (mapcat :features sections))]
    (delete-dir-contents "resources/public/features/")
    `(do ~@(map (fn [feature]
                  (let [contributions (map :name (filter #(contains? (set (mapcat :features (:sections %))) feature) models))]
                    (io/copy (io/file "resources/public/index.html") (io/file (str "resources/public/features/" (sanitize feature) ".html")))
                    `(~'defroute ~(str "/metalib/features/" (sanitize feature) ".html") []
                      (~'swap! ~state ~'assoc :current-page #(~page ~feature (list ~@contributions))))))
                features))))

(defmacro deflanguageroutes [state page]
  (let [models (load-contributions)
        sections (mapcat :sections models)
        languages (set (mapcat :languages sections))]
    (delete-dir-contents "resources/public/languages/")
    `(do ~@(map (fn [language]
                  (let [contributions (map :name (filter #(contains? (set (mapcat :languages (:sections %))) language) models))
                        language (sanitize language)]
                    (io/copy (io/file "resources/public/index.html") (io/file (str "resources/public/languages/" (sanitize language) ".html")))
                    `(~'defroute ~(str "/metalib/languages/" (sanitize language) ".html") []
                       (~'swap! ~state ~'assoc :current-page #(~page ~language (list ~@contributions))))))
                languages))))

(defmacro deftechnologyroutes [state page]
  (let [models (load-contributions)
        sections (mapcat :sections models)
        technologies (set (mapcat :technologies sections))]
    (delete-dir-contents "resources/public/technologies/")
    `(do ~@(map (fn [technology]
                  (let [contributions (map :name (filter #(contains? (set (mapcat :technologies (:sections %))) technology) models))]
                    (io/copy (io/file "resources/public/index.html") (io/file (str "resources/public/technologies/" (sanitize technology) ".html")))
                    `(~'defroute ~(str "/metalib/technologies/" (sanitize technology) ".html") []
                       (~'swap! ~state ~'assoc :current-page #(~page ~technology (list ~@contributions))))))
                technologies))))

(defmacro defconceptroutes [state page]
  (let [models (load-contributions)
        sections (mapcat :sections models)
        concepts (set (mapcat :concepts sections))]
    (delete-dir-contents "resources/public/concepts/")
    `(do ~@(map (fn [concept]
                  (let [contributions (map :name (filter #(contains? (set (mapcat :concepts (:sections %))) concept) models))]
                    (io/copy (io/file "resources/public/index.html") (io/file (str "resources/public/concepts/" (sanitize concept) ".html")))
                    `(~'defroute ~(str "/metalib/concepts/" (sanitize concept) ".html") []
                       (~'swap! ~state ~'assoc :current-page #(~page ~concept (list ~@contributions))))))
                concepts))))

(defmacro contributions []
  (let [model-file-names (-> contribution-dir
                             dir-contents)
        contributions (map #(load-json (str contribution-dir %)) model-file-names)]
    `(list ~@contributions)))
