(ns menu-calculator.core
  (:require [menu-calculator.menu :refer [menu target-price]])
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
  (< target-price (sum-order (cons order item))))

(defn find-combos
  "finds combos"
  [menu total])
