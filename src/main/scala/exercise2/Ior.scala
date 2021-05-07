package exercise2

// sealed trait Ior[A] {
//   def unit[A](left: Throwable, elem: A): Ior[A] = new Ior[A](elem)

//   def flatMap[A](f: (A) => Ior[A]) = this match {
//     case Right(b) => f(b)
//     case Left(a)  => Left(a)
//   }

// //    def map[A](mA: Ior[A])(f: A => B): Ior[B] = flatMap(mA)(a => unit(f(a)))
// }

// case class Left[A](elem: Throwable) extends Ior[A]

// case class Right[A](elem: A) extends Ior[A]

// case class Both[A](left: Throwable, elem: A) extends Ior[A]

// object Ior {
//   def left[A](elem: Throwable): Left[A] = new Left(elem)
//   def right[A](elem: A): Right[A] = new Right(elem)
//   def both[A](left: Throwable, elem: A): Both[A] = new Both(left, elem)
// }


// sealed trait Ior[A] {
//   def flatMap[A, B](a: Ior[A])(f: A => Ior[B]): Ior[B] = a match {
//       case Right(elem) => f(elem)
//       case Both(left, elem) => f(elem)
//       case Left(elem) => Left(elem)
//   }
// }

// case class Left[A](elem: Throwable) extends Ior[A]
// case class Right[A](elem: A) extends Ior[A]
// case class Both[A](left: Throwable, elem: A) extends Ior[A]

// object Ior {
//   def left[A](elem: Throwable): Left[A] = new Left[A](elem)
//   def right[A](elem: A): Right[A] = new Right[A](elem)
//   def both[A](left: Throwable, elem: A): Both[A] = new Both[A](left, elem)

//   def unit[A]: A => Ior[A] = x => Both(new Throwable, x)

//   def map[A, B](mA: Ior[A])(f: A => B): Ior[B] = mA.flatMap(mA)(a => unit(f(a)))
// }

// a.flatMap(x => x*2).flatMap()

sealed trait Ior[A] {
  def flatMap[A](f: A => Ior[A]): Ior[A] = this match {
    case Right(elem)      => f(elem)
    case Both(left, elem) => f(elem)
    case Left(elem)       => Left(elem)
  }

  def map[A](f: A => A): Ior[A] = flatMap(a => Ior.unit(f(a)))
}

case class Left[A](elem: Throwable) extends Ior[A]
case class Right[A](elem: A) extends Ior[A]
case class Both[A](left: Throwable, elem: A) extends Ior[A]

object Ior {
  def left[A](elem: Throwable): Left[A] = Left(elem)
  def right[A](elem: A): Right[A] = Right(elem)
  def both[A](left: Throwable, elem: A): Both[A] = Both(left, elem)

  def unit[A]: A => Ior[A] = x => Both(new Throwable, x)
}
