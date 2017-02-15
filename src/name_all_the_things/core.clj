(ns name-all-the-things.core
  (:require [clojure.math.combinatorics :refer [cartesian-product combinations]]))

(defn any-duplicates? [tuple]
  (boolean
   (some #(apply = %)
         (combinations tuple 2))))

(defn name-tuples
  "Generate n-tuples from the given collections.

  Multiple items from the first collection can appear in the same
  tuple. All following collections will contribute at most one item to
  any tuple."
  [n main-group & groups]
  (let [all-groups (concat groups (map hash-set (set main-group)))]
    (set
     (->> (combinations all-groups n)
          (mapcat #(apply cartesian-product %))
          (remove any-duplicates?)))))
