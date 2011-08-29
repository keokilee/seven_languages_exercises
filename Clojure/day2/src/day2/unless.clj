(ns day2.unless)

(defmacro unless [test body else]
    (list 'if (list 'not test) body '(list 'else))
)
