(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.10.5" :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.5.2")
(def +version+ (str +lib-version+ "-0"))


(task-options!
 pom  {:project     'cljsjs/react-bootstrap
       :version     +version+
       :description "The most popular front-end framework, rebuilt for React."
       :url         "https://react-bootstrap.github.io/index.html"
       :scm         {:url "https://github.com/cljsjs/packages"}
       :license     {"MIT" "https://raw.githubusercontent.com/react-bootstrap/react-bootstrap/master/LICENSE"}})

(deftask package []
  (comp
    (download :url (format "https://unpkg.com/react-bootstrap@%s/dist/react-bootstrap.js" +lib-version+))
    (download :url (format "https://unpkg.com/react-bootstrap@%s/dist/react-bootstrap.min.js" +lib-version+))
    (sift :move {#"^react-bootstrap.js$"
                 "cljsjs/react-bootstrap/development/react-bootstrap.inc.js"
                 #"^react-bootstrap.min.js$"
                 "cljsjs/react-bootstrap/production/react-bootstrap.min.inc.js"})
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.react-bootstrap"
               :requires ["cljsjs.react"
                          "cljsjs.react.dom"])
    (pom)
    (jar)
    (validate)))
