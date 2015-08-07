// This is a Functor
trait Functor[M[_]] {

  /* convert f into a function mapping M[A] to M[B]
   * eg. if M were List, and f was Int ⇒ String
   * fmap would yield List[Int] ⇒ List[String]
   */
  def fmap[A, B](f: A ⇒ B): M[A] ⇒ M[B]
}


