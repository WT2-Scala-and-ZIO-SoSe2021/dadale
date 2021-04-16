import org.scalatest.flatspec.AnyFlatSpec

class Testing extends AnyFlatSpec {
  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  "Test" should "be Test" in {
    val test = "Test"
    assert(test.equals("Test"))
  }
}