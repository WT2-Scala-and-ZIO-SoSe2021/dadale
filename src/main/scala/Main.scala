import scala.annotation.switch
object Main extends App {
  def max(arr: Array[Int]): Int = arr.reduce((x, y) => x max y)

  def min(arr: Array[Int]): Int = arr.reduce((x, y) => x min y)

  def sum(arr: Array[Int]): Int = arr.reduce((x, y) => x + y)

  def parse(card: String): Int = {
    card match {
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

  def optimisticF = determineHandValue(max) _
  
  def pessimisticF = determineHandValue(min) _

  def determineBestHandValue(hand: Array[Int]): Int = {
    // Aces won't ever be counted as 11 more than once 
    // => assign all to 1 apart from last ace
    if(hand.filter(h => h == 11).length > 1) {
      for (i <- 0 until hand.filter(h => h == 11).length - 1) {
        hand(hand.indexWhere(_ == 11)) = 1
      }
    }
    if (isBust(optimisticF(hand))) return pessimisticF(hand)

    return optimisticF(hand)
  }
}
