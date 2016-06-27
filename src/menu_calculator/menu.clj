(ns menu-calculator.menu
  (:require [clojure.data.csv :as csv]))

(defn load-menu
  "reads the file at the given path"
  [filename]
  (slurp filename))

(defn menu-items-to-vectors
  "gives a sequence with each line as a vector"
  [menu]
  (csv/read-csv menu))

(defn strip-currency-symbols
  "strips common currency characters and returns a float"
  [price]
  (read-string (clojure.string/replace price #"[$€£¢]" "")))

(def raw-menu
  (menu-items-to-vectors (load-menu "resources/menu.txt")))

(def target-price
  (strip-currency-symbols ((first raw-menu) 0)))

(def menu-map
  (into {} (rest raw-menu)))

(def menu
  (zipmap (keys menu-map) (map strip-currency-symbols (vals menu-map))))
