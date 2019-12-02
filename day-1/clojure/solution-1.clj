; -- Tyler Wilding

(defn read-input [file]
  (for [line (clojure.string/split-lines (slurp file))]
    (->> (Integer/parseInt line))))

(defn mass-convolution [n]
  (let [quotient (int (/ n 3))]
    (- quotient 2)))

(defn fuel-for-fuel [n]
  (let [mass-req (mass-convolution n)]
    (if (<= mass-req 0)
      0
      (+ mass-req (fuel-for-fuel mass-req)))))

(defn part-one
  []
  (apply + (map mass-convolution (read-input "resources/input"))))

(defn part-two
  []
  (apply + (map fuel-for-fuel (read-input "resources/input"))))