(ns name-all-the-things.core-test
  (:require [clojure.test :as t]
            [name-all-the-things.core :as sut]))

(t/deftest test-cartesian-product
  (t/is (= #{[1 2] [2 1]}
           (sut/cartesian-product #{1} #{2}))))
