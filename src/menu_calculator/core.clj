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

(def memo-gen-check
  (memoize gen-and-check-subs))

(def lowest-price
  "lowest price found on the menu"
  (apply min (vals menu)))

(defn distinct-orders
  "removes duplicates from selections"
  [menu-combos]
  (distinct (map sort (menu-combos))))

(defn all-selections
  "gets all possible selections for a given price and menu"
  [menu]
  (loop [items (vals menu)
         accum []
         count 0]
    (if (>= count (/ target-price lowest-price))
      (distinct (map sort accum))
      (recur items (concat accum (gen-and-check-subs count)) (inc count)))))

(defn find-combos
  "finds combos"
  [menu total])

(defn -main
  [& args]
  (prn (all-selections menu)))
