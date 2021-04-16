import org.scalatest.flatspec.AnyFlatSpec

class Testing extends AnyFlatSpec {
  val tester = Main;

  "Int Array Functions" should "work" in {
    val arr = Array(2, 5, 7, 1);

    assert(tester.max(arr) == 7)
    assert(tester.min(arr) == 1)
    assert(tester.sum(arr) == 15)
  }

  "Parse Functions" should "work" in {
    val cards = Array("2", "5", "7", "A", "K")

    assert(tester.parse("5") == 5)
    assert(tester.parse("A") == 11)
    assert(tester.parse("Q") == 10)

    val parsedCards = tester.parseAll(cards)
    assert(parsedCards(0) == 2)
    assert(parsedCards(3) == 11)
  }

  "Values Function" should "work" in {
    assert(tester.values(4)(0) == 4 && tester.values(4).length == 1)
    assert(tester.values(11)(0) == 1 && tester.values(11)(1) == 11 && tester.values(11).length == 2)
  }

  "Hand Value Functions" should "work" in {
    def strategy(arr: Array[Int]): Int = arr(0)
    val hand = Array(2, 11)
    val hand2 = Array(2, 5)

    val handVal = tester.determineHandValue(strategy)_
    assert(handVal(hand) == 3)
    assert(handVal(hand2) == 7)

    assert(tester.isBust(12) == false)
    assert((tester.isBust(23) == true))
  }

  "Opt & Pess Functions" should "work" in {
    val hand = Array(2, 11)

    assert(tester.optimisticF(hand) == 13)
    assert(tester.pessimisticF(hand) == 3)
  }

  "Best Hand Function" should "work" in {
    val hand1 = Array(10, 11)
    val hand2 = Array(10, 2, 11)

    assert(tester.determineBestHandValue(hand1) == 21)
    assert(tester.determineBestHandValue(hand2) == 13)

    // Bonus task test cases
    val hand3 = Array(9, 11, 11)
    val hand4 = Array(11, 11, 11)

    assert(tester.determineBestHandValue(hand3) == 21)
    assert(tester.determineBestHandValue(hand4) == 13)
  }
}