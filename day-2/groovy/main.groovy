List.metaClass.eachOpcodeWithTermination = { closure ->
    // Go through elements in 4-tuples until `99` is encountered
    count = 0
    for ( value in delegate ) {
        // If opcode is `99` then we terminate
        if ( count % 4 == 0 && value == 99) {
          break
        } else if (count % 4 == 0) {
          closure(value, count)
        }
        count++
    }
}

def stepThrough(coll, val1, val2) {
  // Modify the first 2 values to what is provided
  coll[1] = val1
  coll[2] = val2
  // Mutate collection
  println coll
  correctCollection(coll)
  println coll
}

def correctCollection(coll) {
  coll.eachOpcodeWithTermination{ it, i ->
    if (i % 4 == 0) {
      opCode = coll[i]
      val1 = coll.get(coll[i+1])
      val2 = coll.get(coll[i+2])
      dest = coll[i+3]
      // If opcode is `1` then we perform addition, otherwise multiply
      result = opCode == 1 ? (val1 + val2) : (val1 * val2)
      coll[dest] = result
    }
  }
}

// [1, 2, 3, 4, 5, 6, 7, 8, 99, 10, 11, 12].eachOpcodeWithTermination { it, i ->
//   println it + " " + i
// }

// Write test cases eventually
// testCollection = [1,1,1,4,99,5,6,0,99]
// correctCollection(testCollection)
// print testCollection

// Read in File
input = 'workdir/input'
File fileHandler = new File(input)
text = fileHandler.getText('UTF-8').trim()

// Convert to list of ints
vals = text.split(',').collect{
  Integer.parseInt(it)
}

stepThrough(vals, 12, 2)