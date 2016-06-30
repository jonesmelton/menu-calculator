(ns menu-calculator.core
  (:require [menu-calculator.menu :as menu]
            [menu-calculator.state :refer [menu-path]]
            [clojure.pprint :refer [pprint]])
  (:gen-class))

(defn price-check
  "checks if a set of prices totals to the correct cost"
  [price-set]
  (= (menu/target-price) (reduce + price-set)))

(defn max-combinations
  "returns the maximum possible number of items in a valid order"
  []
;; +1 because it's for a range and clj ranges are exclusive only
  (+ 1 (/ (menu/target-price) (menu/lowest-price))))

(defn make-combos
  "generate all combos of a given length"
  [sequence seq-length]
  (when-let [[car & cdr] sequence]
    (if (= seq-length 1)
      (map list sequence)
      (concat (map (partial cons car) (make-combos sequence (dec seq-length)))
              (make-combos cdr seq-length)))))

(defn make-all-combos
  "lazily generates combinations until one totals to the target price"
  []
  (->> (max-combinations)
       (range 1)
       (pmap #(make-combos (vals (menu/menu)) %))
       (apply concat)
       (filter price-check)
       (first)))
;;^ remove (first) to find ALL possible orders

(defn combo-names
  "shows the names of the things to order"
  []
  (frequencies (map (partial get (menu/inverted-menu)) (make-all-combos))))

(defn -main
  [& args]
  (pprint (combo-names)))
