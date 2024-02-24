(ns AoC-2
  (:require [clojure.string :as str]))

(def read-str
  (->>
   (->
    (slurp "input-2.txt")
    (str/split #"\r\n"))
   (map #(str/split % #""))))

(defn findfrq [strlst]
  (let [frq (reduce
         (fn [nums char]
           (update-in nums [char] (fnil inc 0)))
         {} strlst)]
    [(if (some #(= % 2) (vals frq)) 1 0) (if (some #(= % 3) (vals frq)) 1 0)]))

(->>
 read-str
 (reduce 
  (fn [res reading]  
    (let [frq (findfrq reading)]  
      (map + res frq)))  
  [0 0])
 (reduce *))