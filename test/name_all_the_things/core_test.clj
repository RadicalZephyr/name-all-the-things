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

(t/deftest test-all-cartesian-product-pairs
  (t/is (= #{[1  'c] ['c  1]
             [:a 'c] ['c :a]
             [:a  1] [ 1 :a]}
           (sut/all-cartesian-product-pairs #{}
                                            #{1}
                                            #{:a}
                                            #{'c})))

  (t/is (= #{["def" "abc"] ["abc" "def"]}
           (sut/all-cartesian-product-pairs #{"abc" "def"})))

  (t/is (= #{["def" "abc"] ["abc" "def"]
             ["def" 1] [1 "def"]
             ["abc" 1] [1 "abc"]}
           (sut/all-cartesian-product-pairs #{"abc" "def"}
                                            #{1}))))

(t/deftest test-all-cartesian-product-triples
  (t/is (= #{[1  :a 'c]
             [1  'c :a]
             ['c  1 :a]
             ['c  :a 1]
             [:a  'c 1]
             [:a  1 'c]}
           (sut/all-cartesian-product-triples #{}
                                              #{1}
                                              #{:a}
                                              #{'c})))

  (t/is (= #{}
           (sut/all-cartesian-product-triples #{"abc" "def"})))

  (t/is (= #{[1 "def" "abc"]
             [1 "abc" "def"]
             ["abc" 1 "def"]
             ["abc" "def" 1]
             ["def" "abc" 1]
             ["def" 1 "abc"]}
           (sut/all-cartesian-product-triples #{"abc" "def"}
                                              #{1})))

  (t/is (= #{[1 "def" "abc"]
             [1 "abc" "def"]
             ["abc" 1 "def"]
             ["abc" "def" 1]
             ["def" "abc" 1]
             ["def" 1 "abc"]
             [2 "def" "abc"]
             [2 "abc" "def"]
             ["abc" 2 "def"]
             ["abc" "def" 2]
             ["def" "abc" 2]
             ["def" 2 "abc"]}
           (sut/all-cartesian-product-triples #{"abc" "def"}
                                              #{1 2}))))
