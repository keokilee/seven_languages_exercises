(ns day2.test.unless
  (:use [day2.unless] :reload)
  (:use [clojure.test]))

(deftest unless-test 
  (is true (unless false (list true) (list "error")))
)

(deftest unless-else
  (is true (unless true (list "error") (list true)))
)
