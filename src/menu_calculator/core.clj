(ns menu-calculator.core
  (:require [menu-calculator.menu :refer [menu target-price]]
            [clojure.math.combinatorics :as c])
  (:gen-class))

(defn price-check
  "checks if a set of prices totals to the correct cost"
  [price-set]
  (= target-price (reduce + price-set)))

(defn gen-and-check-subs
  "generates a lazy seq of selections and filters for correct total price"
  [item-count]
  (filter price-check (c/selections (vals menu) item-count)))

(def lowest-price
  "lowest price found on the menu"
  (apply min (vals menu)))

(defn distinct-orders
  "removes duplicates from selections"
  [menu-combos]
  (distinct (map sort (menu-combos))))

(def find-combos
  "finds combos"
  (->> (/ target-price lowest-price)
       (range 1)
       (map #(gen-and-check-subs %))
       (apply concat)
       (map sort)
       (distinct)))

(defn -main
  [& args]
  (prn find-combos))
