val fruits = Seq("apple", "banana", "orange")

fruits.map(x=> x.toUpperCase)

fruits.flatMap(_.toUpperCase)