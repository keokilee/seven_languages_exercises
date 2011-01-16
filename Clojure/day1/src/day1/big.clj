(ns day1.big)

(defn big
  "Returns true if str is longer than n characters."
  [str, n]
  (< n (count str))
)