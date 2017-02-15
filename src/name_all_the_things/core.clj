(ns name-all-the-things.core
  (:require [clojure.math.combinatorics :refer [combinations]]))

(defn all-pairs-equality-checks [item-syms]
  `(and
    ~@(map (fn [[x y]] `(not= ~x ~y)) (combinations item-syms 2))))

(defmacro cartesian-product* [include-dups? & colls]
  (let [item-syms (repeatedly (count colls) #(gensym "item"))]
    `(set
      (for ~(vec (concat
                  (interleave item-syms colls)
                  (when-not include-dups?
                    [:when (all-pairs-equality-checks item-syms)])))
        ~(vec item-syms)))))

(defn cartesian-product
  ([a b]
   (cartesian-product* false a b))
  ([a b c]
   (cartesian-product* false a b c)))

(defn all-cartesian-product-pairs
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

(defn all-cartesian-product-triples
  "Generate the set of all cartesian products of the given sets.

  The Cartesian square of the first set is included in the set of
  pairs returned. The remaining
  "
  [coll & groups]
  (let [all-groups (conj groups coll)]
    (set
     (->> (cartesian-product all-groups all-groups all-groups)
          (mapcat #(apply cartesian-product %))
          (concat (cartesian-product* true coll coll coll))))))
;; Probably the solution for avoiding dups is to do it here at the
;; end.  So instead of using the :when filter in the cartesian product
;; for generator, post-filter for none of the pairs being equal

;; Also it's not actualy cartesian product that should be selecting
;; what to call cartesian product on. It should be combinations again,
;; and then the way we get choose 2 or 3 words is to take 2 or 3 size
;; combinations.

;; Also, the way to deal with initial collection being different is to
;; add it to the group by splitting each item inside it into an
;; individual set.
