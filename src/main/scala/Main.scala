import scala.annotation.switch
object Main extends App {
  def max(arr: Array[Int]): Int = arr.reduceLeft((x, y) => x max y)

  def min(arr: Array[Int]): Int = arr.reduceLeft((x, y) => x min y)

  def sum(arr: Array[Int]): Int = arr.reduceLeft((x, y) => x + y)

  def parse(card: String): Int = {
    card match {
      // "A", Jack - "J", Queen - "Q", King - "K"
      case "A" => 11
      case "J" | "Q" | "K" => 10
      case _ => card.toInt
    }
  }

  def parseAll(arr: Array[String]): Array[Int] = arr.map(s => parse(s))

  def values(card: Int): Array[Int] = {
    card match {
      case 11 => Array(1, 11)
      case _ => Array(card)
    }
  }

  def determineHandValue(strategy: Array[Int] => Int)(hand: Array[Int]): Int = {
    return sum(hand.map(h => values(h)).map(h => strategy(h)))
  }

  def isBust(value: Int): Boolean = {
    return value > 21
  }
}
