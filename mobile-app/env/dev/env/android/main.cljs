(ns ^:figwheel-no-load env.android.main
  (:require [om.next :as om]
            [mobile-app.android.core :as core]
            [mobile-app.state :as state]
            [figwheel.client :as fw]
            [env.config :as conf]))

(enable-console-print!)

(assert (exists? core/init) "Fatal Error - Your core.cljs file doesn't define an 'init' function!!! - Perhaps there was a compilation failure?")
(assert (exists? core/app-root) "Fatal Error - Your core.cljs file doesn't define an 'app-root' function!!! - Perhaps there was a compilation failure?")

(fw/start {
           :websocket-url    (:android conf/figwheel-urls)
           :heads-up-display false
           :jsload-callback  #(om/add-root! state/reconciler core/AppRoot 1)})

(core/init)

;; Do not delete, root-el is used by the figwheel-bridge.js
(def root-el (core/app-root))
