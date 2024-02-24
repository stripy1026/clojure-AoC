(ns AoC-2
  (:require [clojure.string :as str]))

(def read-str
  (->>
   (->
    (slurp "input-2.txt")
    (str/split #"\r\n"))
   (map #(str/split % #""))))

(defn findfrq [strlst]
     (loop [chars strlst
            nums {}]
       (if (first chars)
         (if (contains? nums (first chars))
           (recur (next chars) (update-in nums [(first chars)] inc))
           (recur (next chars) (assoc nums (first chars) 1)))
         nums)))

(loop [reading read-str 
       two 0
       three 0] 
  (if (first reading)
    (let [frq (findfrq (first reading))]
      (if (and (some #(= % 2) (vals frq)) (some #(= % 3) (vals frq)))
        (recur (next reading) (inc two) (inc three))
        (if (some #(= % 2) (vals frq))
          (recur (next reading) (inc two) three)
          (if (some #(= % 3) (vals frq))
            (recur (next reading) two (inc three))
            (recur (next reading) two three)))))
    (* two three)))