(ns menu-calculator.core
  (:require [menu-calculator.menu :refer [menu target-price]]
            [clojure.math.combinatorics :as c])
  (:gen-class))

(defn -main
  [& args]
  (println args))

(defn sum-order
  "tallies the cost of an order"
  [order]
  (reduce + (vals order)))

(defn item-fits?
  "checks if given item fits in given order"
  [order item]
  (< target-price (sum-order (apply assoc order item))))

(def flip-menu
  "swaps the keys and values of the menu hmap"
  (clojure.set/map-invert menu))

(defn price-check
  "checks if a set of prices totals to the correct cost"
  [price-set]
  (= target-price (reduce + price-set)))                  

(defn gen-and-check-subs
  "generates a lazy seq of selections and filters for correct total price"
  [item-count]
  (filter price-check (c/selections (vals menu) item-count)))

(defn find-combos
  "finds combos"
  [menu total])


