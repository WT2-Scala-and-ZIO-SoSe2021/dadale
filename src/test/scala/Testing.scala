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
    val cards = Array("2", "5", "7", "A", "K");

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
}