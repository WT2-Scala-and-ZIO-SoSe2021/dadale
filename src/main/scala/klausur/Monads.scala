package klausur

object Monads {
  def main(args: Array[String]): Unit = {
    val p = Person("", Some("granger@hogwarts.edu"))
    val tmp = p.email.map(Ops.fetch).map(x => x.toOption.map(Ops.firstName))
    println(tmp)
    val tmp2 =
      p.email.map(Ops.fetch).flatMap(x => x.toOption.map(Ops.firstName))
    println(tmp2)
    val tmp3 = p.email
      .map(Ops.fetch)
      .map(x => {
        val l = x.toOption match {
          case Some(i) => Ops.firstName(i)
          case None    => None
        }
        l
      })
    println(tmp3)
  }
}

case class Person(name: String, email: Option[String])

object Ops {
  // def fetch(email: String): Either[Throwable, Person] = Left(
  //   new Throwable("Incorrect parameters")
  // )

  def fetch(email: String): Either[Throwable, Person] = Right(
    Person("Hermione", Some(email))
  )

  def firstName(p: Person): String = p.name
}
