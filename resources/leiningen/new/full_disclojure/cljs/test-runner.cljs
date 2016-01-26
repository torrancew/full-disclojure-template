(ns {{name}}.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]))

(doo-all-tests #"^{{name}}\..*?-test")
