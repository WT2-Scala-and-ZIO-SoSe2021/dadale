import org.scalatest.flatspec.AnyFlatSpec

class Testing extends AnyFlatSpec {
  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  "Test" should "be Test" in {
    val test = "Test"
    assert(test.equals("Test"))
  }

  "Main Functions" should "work" in {
    val tester = Main;
    val arr = Array(2, 5, 7, 1);

    assert(tester.max(arr) == 7)
    assert(tester.min(arr) == 1)
    assert(tester.sum(arr) == 15)
  }
}