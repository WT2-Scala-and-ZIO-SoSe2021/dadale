package klausur

object Reduce {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4)
    println(list.reduce((a, b) => if (a > b) a else b))
    // println(
    //   list.reduceLeft((a, b) => (a + b).toString)
    // )
  }
}
