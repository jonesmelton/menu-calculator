(ns menu-calculator.core
  (:require [menu-calculator.menu :refer [menu target-price lowest-price]]
            [clojure.math.combinatorics :as c])
  (:gen-class))

(defn price-check
  "checks if a set of prices totals to the correct cost"
  [price-set]
  (= (target-price) (reduce + price-set)))

(defn max-combinations
  "returns the maximum possible number of items in a valid order"
  []
;; +1 because it's for a range and clj ranges are exclusive only
  (+ 1 (/ (target-price) (lowest-price))))

(defn gen-and-check-subs
  "generates a lazy seq of selections and filters for correct total price"
  [item-count]
  (->> item-count
       (c/selections (vals (menu)))
       (filter price-check)))
      "replace with combinations + duplicates from internet"

(defn make-combos
  "generate all combos of a given length"
  [sequence seq-length]
  (when-let [[car & cdr] sequence]
    (if (= seq-length 1)
      (map list sequence)
      (concat (map (partial cons car) (make-combos sequence (dec seq-length)))
              (make-combos cdr seq-length)))))

(defn make-all-combos 
  "generates all combos of a given length or shorter"
  []
  )

(defn find-combos
  "finds combos"
  []
  (->> (max-combos)
       (range 1)
       (map #(make-combos (vals (menu)) %))
       (apply concat)
       (map sort)
       (distinct)
       (first)))

(defn -main
  [& args]
  (prn (find-combos)))
