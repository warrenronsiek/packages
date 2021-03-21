# cljsjs/draft-js

This jar comes with `deps.cljs` as used by the [Foreign Libs][flibs] feature
of the Clojurescript compiler. After adding the above dependency to your project
you can require the packaged library like so:

```clojure
(ns application.core
  (:require cljsjs.draft-js))
```

This library has an unmet peer dependency on react and react-dom. This is a
feature in the event that you want to use your own react version or if it is
provided by some other library (e.g. reagent). Hence, you need `deps.cljs` of
the form:

```clojure
cljsjs/draft-js {:mvn/version "0.11.7-0" :exclusions [cljsjs/react-dom cljsjs/react]}
cljsjs/react {:mvn/version "17.0.1-0"}
cljsjs/react-dom {:mvn/version "17.0.1-0"}
cljsjs/react-dom-server {:mvn/version "17.0.1-0"}}
```
