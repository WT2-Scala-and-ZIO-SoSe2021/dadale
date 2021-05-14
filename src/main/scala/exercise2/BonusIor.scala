package exercise2

sealed trait BonusIor[A] {

  def flatMap[B](f: A => BonusIor[B]): BonusIor[B] = this match {
    case Right(elem) => f(elem)
    case Both(left, elem) =>
      f(elem) match {
        case Right(elem)      => Both(left, elem)
        case Both(left, elem) => Both(left, elem)
        case Left(elem)       => Left(elem)
      }
    case Left(elem) => Left(elem)
  }
  def map[B](f: A => B): BonusIor[B] = flatMap((a: A) => BonusIor.unit(f(a)))
}

case class Left[A](elem: Throwable) extends BonusIor[A]
case class Right[A](elem: A) extends BonusIor[A]
case class Both[A](left: Throwable, elem: A) extends BonusIor[A]

object BonusIor {
  def left[A](elem: Throwable): Left[A] = Left(elem)
  def right[A](elem: A): Right[A] = Right(elem)
  def both[A](left: Throwable, elem: A): Both[A] = Both(left, elem)

  def unit[A](elem: A): BonusIor[A] = Right(elem)
}
