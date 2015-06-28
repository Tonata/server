import java.io.Serializable

sealed trait Color
final case object Red extends Color
final case object Green extends Color
final case object Blue extends Color

val colors = List(Red, Green, Blue)


