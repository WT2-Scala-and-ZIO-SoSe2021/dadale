package exercise2.bonus

sealed trait Ior[E, A] {

  def flatMap[C](f: A => Ior[E, C]): Ior[E, C] = this match {
    case Right(elem) => f(elem)
    case Both(left, elem) =>
      f(elem) match {
        case Right(elem)      => Both(left, elem)
        case Both(left, elem) => Both(left, elem)
        case Left(elem)       => Left(elem)
      }
    case Left(elem) => Left(elem)
  }

  def map[C](f: A => C): Ior[E, C] = flatMap((a: A) => Ior.unit(f(a)))
}

case class Left[E, A](elem: E) extends Ior[E, A]
case class Right[E, A](elem: A) extends Ior[E, A]
case class Both[E, A](left: E, elem: A) extends Ior[E, A]

object Ior {
  def left[E, A](elem: E): Left[E, A] = Left(elem)
  def right[E, A](elem: A): Right[E, A] = Right(elem)
  def both[E, A](left: E, elem: A): Both[E, A] = Both(left, elem)

  def unit[E, A](elem: A): Ior[E, A] = Right(elem)
}
