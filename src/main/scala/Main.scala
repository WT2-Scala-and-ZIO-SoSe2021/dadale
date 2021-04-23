import scala.annotation.switch
object Main extends App {
  def max(arr: Array[Int]): Int = arr.reduce(_ max _)

  def min(arr: Array[Int]): Int = arr.reduce(_ min _)

  def sum(arr: Array[Int]): Int = arr.reduce(_ + _)

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

  def determineHandValue(strategy: Array[Int] => Int)(hand: Array[Int]): Int = 
    sum(hand.map(h => values(h)).map(h => strategy(h)))
  

  def isBust(value: Int): Boolean = value > 21

  def optimisticF = determineHandValue(max) _
  
  def pessimisticF = determineHandValue(min) _

  def determineBestHandValue(hand: Array[Int]): Int = {
    // Aces won't ever be counted as 11 more than once 
    // => assign all to 1 apart from first ace
    val replacedHand = hand.map(h => if (h == 11) 1 else h)
    val firstAceIdx = replacedHand.indexOf(1)
    if (firstAceIdx != -1) replacedHand(firstAceIdx) = 11

    val optimisticValue = optimisticF(replacedHand)

    if (isBust(optimisticValue)) pessimisticF(replacedHand) else optimisticValue
  }
}
