(ns day1.test.collection_type
  (:use [day1.collection_type] :reload)
  (:use [clojure.test]))

(deftest collection-type-vector
  (is (= :vector (collection-type [1, 2, 3])))
)

(deftest collection-type-map
  (is (= :map (collection-type {:foo 1, :bar 2})))
)

(deftest collection-type-set
  (is (= :set (collection-type #{1, 2, 3})))
)

(deftest collection-type-invalid
  (is (nil? (collection-type "foo")))
)
