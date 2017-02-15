(ns name-all-the-things.core)

(defn cartesian-product [a b]
  (set
   (apply concat
          (for [x a
                y b]
            [[x y] [y x]]))))
