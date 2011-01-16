(ns day1.test.big
  (:use [day1.big] :reload)
  (:use [clojure.test]))

(deftest big-test-empty
  (is (false? (big "" 3)))
)

(deftest big-test-small
  (is (false? (big "foo" 4)))
)

(deftest big-test-big
  (is (false? (big "hello" 5)))
)