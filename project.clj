(defproject frege-dbc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.frege-lang/frege "3.22.524-gcc99d7e"]]
  :plugins [[lein-fregec "3.22.524"]
            [lein2-eclipse "2.0.0"]]
  :aot :all
  :frege-source-paths ["src/frege"]
  :profiles {:uberjar {:aot :all
                       :prep-tasks ["fregec" "compile"]}})
