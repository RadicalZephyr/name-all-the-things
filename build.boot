(def project 'name-all-the-things)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "1.8.0"]
                            [org.clojure/math.combinatorics "0.1.4"]
                            [adzerk/boot-test "1.2.0" :scope "test"]])

(task-options!
 pom {:project     project
      :version     version
      :description "Generate names from lists of words."
      :scm         {:url "https://github.com/RadicalZephyr/name-all-the-things"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

(require '[adzerk.boot-test :refer [test]])
