(ns name-all-the-things.core)

(defn cartesian-product [a b]
  (set
   (apply concat
          (for [x a
                y b]
            [[x y] [y x]]))))

(defn simple-self-cartesian-product [coll]
  (set
   (for [a coll
         b coll
         :when (not= a b)]
     [a b])))

(defn all-cartesian-products
  ([coll] (simple-self-cartesian-product coll))
  ([coll & groups]
   (set
    (mapcat #(apply cartesian-product %)
            (simple-self-cartesian-product (conj groups coll))))))
