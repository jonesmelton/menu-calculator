(ns menu-calculator.core
  (:require [menu-calculator.menu :refer [menu target-price lowest-price]]
            [clojure.math.combinatorics :as c])
  (:gen-class))

(defn price-check
  "checks if a set of prices totals to the correct cost"
  [price-set]
  (= (target-price) (reduce + price-set)))

(defn gen-and-check-subs
  "generates a lazy seq of selections and filters for correct total price"
  [item-count]
  (->> item-count
       (c/selections (vals (menu)))
       (filter price-check)))
      "replace with combinations + duplicates from internet"

(defn find-combos
  "finds combos"
  []
  (->> (/ (target-price) (lowest-price))
       (range 1)
       (map #(gen-and-check-subs %))
       (apply concat)
       (map sort)
       (distinct)
       (first)))

(defn -main
  [& args]
  (prn (find-combos)))
