(ns AoC-1
  (:require [clojure.string :as str]))

;;1
(def read-num
  (->>
   (str/split (slurp "input-1.txt") #"\r\n")
   (map #(Integer/parseInt %))))

(reduce + read-num)

;;2
(def num-cycle (cycle read-num))
(loop [cur 0
       nums #{}
       num-cycle-iter num-cycle]
  (let [sum (+ cur (first num-cycle-iter))]
  (if (contains? nums sum)
    sum 
    (recur sum (conj nums sum) (next num-cycle-iter)))))
