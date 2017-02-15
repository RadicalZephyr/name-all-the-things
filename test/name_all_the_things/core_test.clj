(ns name-all-the-things.core-test
  (:require [clojure.test :as t]
            [name-all-the-things.core :as sut]))

(t/deftest test-any-duplicates
  (t/is (= true
           (sut/any-duplicates? [1 1])))
  (t/is (= true
           (sut/any-duplicates? [2 1 1])))
  (t/is (= true
           (sut/any-duplicates? [1 2 1])))


  (t/is (= false
           (sut/any-duplicates? [1 2])))
  (t/is (= false
           (sut/any-duplicates? [1 2 3]))))

(t/deftest test-name-tuples
  (t/testing "n == 2"
    (t/is (= #{[1  'c] [:a 'c] [ 1 :a]}
             (sut/name-tuples 2
                              #{}
                              #{1}
                              #{:a}
                              #{'c})))

    (t/is (= #{["abc" "def"]}
             (sut/name-tuples 2
                              #{"abc" "def"})))

    (t/is (= #{["abc" "def"] [1 "def"] [1 "abc"]}
             (sut/name-tuples 2
                              #{"abc" "def"}
                              #{1})))

    (t/is (= #{["abc" "def"]}
             (sut/name-tuples 2
                              ["abc" "def" "abc"])))

    (t/is (= #{["abc" "def"]}
             (sut/name-tuples 2
                              #{"abc" "def"}
                              ["abc"]))))

  (t/testing "n == 3"
    (t/is (= #{[1  :a 'c]}
             (sut/name-tuples 3
                              #{}
                              #{1}
                              #{:a}
                              #{'c})))

    (t/is (= #{}
             (sut/name-tuples 3
                              #{"abc" "def"})))

    (t/is (= #{[1 "abc" "def"]}
             (sut/name-tuples 3
                              #{"abc" "def"}
                              #{1})))

    (t/is (= #{[1 "abc" "def"]
               [2 "abc" "def"]}
             (sut/name-tuples 3
                              #{"abc" "def"}
                              #{1 2})))))
