(ns name-all-the-things.core-test
  (:require [clojure.test :as t]
            [name-all-the-things.core :as sut]))

(t/deftest test-all-pairs-equality-checks
  (t/is (= '(clojure.core/and)
           (sut/all-pairs-equality-checks [])))

  (t/is (= '(clojure.core/and)
           (sut/all-pairs-equality-checks ['a])))

  (t/is (= '(clojure.core/and (clojure.core/not= a b))
           (sut/all-pairs-equality-checks ['a 'b])))

  (t/is (= '(clojure.core/and (clojure.core/not= a b)
                              (clojure.core/not= a c)
                              (clojure.core/not= b c))
           (sut/all-pairs-equality-checks ['a 'b 'c]))))

(t/deftest test-all-cartesian-products
  (t/is (= #{[1  'c] ['c  1]
             [:a 'c] ['c :a]
             [:a  1] [ 1 :a]}
           (sut/all-cartesian-products #{}
                                       #{1}
                                       #{:a}
                                       #{'c})))

  (t/is (= #{["def" "abc"] ["abc" "def"]}
           (sut/all-cartesian-products #{"abc" "def"})))

  (t/is (= #{["def" "abc"] ["abc" "def"]
             ["def" 1] [1 "def"]
             ["abc" 1] [1 "abc"]}
           (sut/all-cartesian-products #{"abc" "def"}
                                       #{1}))))
