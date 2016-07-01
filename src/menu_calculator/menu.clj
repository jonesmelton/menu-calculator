(ns menu-calculator.menu
  (:require [clojure.data.csv :as csv]
            [clojure.set :refer [map-invert]]))

(defn load-menu
  "reads the file at the given path"
  [filename]
  (slurp filename))

(defn menu-items-to-vectors
  "returns a seq of vectors, one for each line"
  [menu]
  (csv/read-csv menu))

(defn strip-currency-symbols
  "strips common currency characters and returns a float"
  [price]
  (read-string (clojure.string/replace price #"[$€£¢]" "")))

(defn raw-menu []
  (menu-items-to-vectors (load-menu "resources/menu.txt")))

(defn target-price []
  (strip-currency-symbols ((first (raw-menu)) 0)))

(defn menu-map []
  (into {} (rest (raw-menu))))

(defn menu []
  (zipmap (keys (menu-map)) (map strip-currency-symbols (vals (menu-map)))))

(defn lowest-price
  "lowest price found on the menu"
  []
  (apply min (vals (menu))))

(defn inverted-menu
  "swaps menu keys and values"
  []
  (map-invert (menu)))
