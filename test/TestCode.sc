import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import scala.async.Async.{async, await}

val (f1,f2,f3) = (Future {1}, Future {2}, Future {3})

lazy val res = async{
  await {f1} + await {f2} + await {f3}

}
println("The Server is ",res)

