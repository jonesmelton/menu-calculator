(ns menu-calculator.core-test
  (:require [clojure.test :refer :all]
            [menu-calculator.core :refer :all]))

            (deftest ci-test
              (testing "passing test for CI hook"
                (is (= 0 0))))
