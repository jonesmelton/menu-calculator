(ns menu-calculator.menu
  (:require [clojure.data.csv :as csv]))

(defn load-menu [filename]
  (slurp filename))

(defn menu-items-to-vectors [menu]
  (csv/read-csv menu))

(defn strip-currency-symbols [price]
  "strips common currency characters and returns a float"
  (read-string (clojure.string/replace price #"[$€£¢]" "")))

(def raw-menu
  (menu-items-to-vectors (load-menu "resources/menu.txt")))

(def target-price
  (strip-currency-symbols ((first raw-menu) 0)))

(def menu-map
  (into {} (rest raw-menu)))

(def menu
  (zipmap (keys menu-map) (map strip-currency-symbols (vals menu-map))))
