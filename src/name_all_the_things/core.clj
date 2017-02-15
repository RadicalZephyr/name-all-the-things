(ns name-all-the-things.core)

(defn cartesian-product [a b]
  (set
   (for [x a
         y b
         :when (not= x y)]
     [x y])))

(defn all-cartesian-products
  "Generate the set of all cartesian products of the given sets.

  The Cartesian square of the first set is included in the set of
  pairs returned. The remaining
  "
  [coll & groups]
  (let [all-groups (conj groups coll)]
    (set
     (->> (cartesian-product all-groups all-groups)
          (mapcat #(apply cartesian-product %))
          (concat (cartesian-product coll coll))))))
