(ns menu-calculator.core
  (:require [clojure.data.csv :as csv])
  (:gen-class))

(defn load-menu [filename]
  (slurp filename))

(defn menu-items-to-vectors [menu]
  (csv/read-csv menu))

(defn strip-currency-symbols [price]
  "strips common currency characters and returns a float"
  (read-string (clojure.string/replace price #"[$€£¢]" "")))

(def menu
  (menu-items-to-vectors (load-menu "resources/menu.txt")))

(def target-price
  (strip-currency-symbols ((first menu) 0)))

(defn menu-into-map [menu]
  (into {} (rest menu)))

(defn -main
  [& args]
  (println target-price))
