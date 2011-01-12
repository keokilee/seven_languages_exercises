(defn collection-type
  "Returns the type of col if it is a built-in collection."
  [col]
  (if (= clojure.lang.PersistentVector (class col)) 
    :vector 
    (if (= clojure.lang.PersistentArrayMap (class col))
      :map 
      (if (= clojure.lang.PersistentHashSet (class col))
        :set "unknown")))
)