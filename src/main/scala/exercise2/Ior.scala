package exercise2

sealed trait Ior[A] {

  def flatMap[B](f: A => Ior[B]): Ior[B] = this match {
    case Right(elem) => f(elem)
    case Both(left, elem) => f(elem) match {
      case Right(elem) => Both(left, elem)
      case Both(left, elem) => Both(left, elem)
      case Left(elem) => Left(elem)
    }
    case Left(elem) => Left(elem)
  }
  def map[B](f: A => B): Ior[B] = flatMap((a: A) => Ior.unit(f(a)))
}

case class Left[A](elem: Throwable) extends Ior[A]
case class Right[A](elem: A) extends Ior[A]
case class Both[A](left: Throwable, elem: A) extends Ior[A]

object Ior {
  def left[A](elem: Throwable): Left[A] = Left(elem)
  def right[A](elem: A): Right[A] = Right(elem)
  def both[A](left: Throwable, elem: A): Both[A] = Both(left, elem)

  def unit[A](elem: A): Ior[A] = Right(elem)
}