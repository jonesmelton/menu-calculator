(ns menu-calculator.core
  (:require [clojure.data.csv :as csv])
  (:gen-class))

(defn load-menu [filename]
  (slurp filename))

(defn menu-items-to-vectors [menu]
  (csv/read-csv menu))

(defn -main
  [& args]
  (println "Hello, World!"))

(def menu 
  (menu-items-to-vectors (load-menu "resources/menu.txt")))

(def target-price
  ((first menu) 0))

(defn menu-into-map [menu]
  (into {} (rest menu)))
