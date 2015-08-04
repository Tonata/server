val fruits = Seq("apple", "banana", "orange")

fruits.map(x=> x.toUpperCase)

fruits.flatMap(_.toUpperCase)

implicit val minOrdering = Ordering.fromLessThan[Int](_ > _)

val l = List(2,4,7,1,3,9,10).sorted

l filter(x=>x > 4)


class MyBox[T](val value:T)

val boxedString:MyBox[String] = new MyBox[String]("Hello")



def rawLengthOf(a:String):Int = a.length
