(ns name-all-the-things.core-test
  (:require [clojure.test :as t]
            [name-all-the-things.core :as sut]))

(t/deftest test-all-cartesian-products
  (t/is (= #{[1 :a] [:a 1] [1 'c] ['c 1] [:a 'c] ['c :a]}
           (sut/all-cartesian-products #{}
                                       #{1}
                                       #{:a}
                                       #{'c})))

  (t/is (= #{["abc" "def"]
             ["def" "abc"]}
           (sut/all-cartesian-products #{"abc" "def"})))

  (t/is (= #{["abc" "def"]
             ["def" "abc"]
             ["abc" 1]
             [1 "abc"]
             ["def" 1]
             [1 "def"]}
           (sut/all-cartesian-products #{"abc" "def"}
                                       #{1}))))
