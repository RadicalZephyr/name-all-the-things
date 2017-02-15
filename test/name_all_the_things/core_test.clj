(ns name-all-the-things.core-test
  (:require [clojure.test :as t]
            [name-all-the-things.core :as sut]))

(t/deftest test-cartesian-product
  (t/is (= #{[2 2]}
           (sut/cartesian-product #{2} #{2})))

  (t/is (= #{[1 2] [2 1]}
           (sut/cartesian-product #{1} #{2})))

  (t/is (= #{[1 2] [2 1] [1 3] [3 1]}
           (sut/cartesian-product #{3 2} #{1}))))
