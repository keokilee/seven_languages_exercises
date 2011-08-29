(ns day2.duck)

(defprotocol Bird
  (location [b])
  (migrate [b])
  (talk [b, words])
)

(def locations [:south, :north])

(defn fly
  [location]
  (if (= location 0) 1 0)
)
          
(defn speak
  [words]
  (println (take (count words) (repeat "quack!")))
)

(defrecord Duck [location]
  Bird
  (location [_] (locations location))

  (migrate [_] (Duck. (fly location)))
  (talk [_, words] (speak words))
  Object
  (toString [this] (str "[" (location this) "]"))
)