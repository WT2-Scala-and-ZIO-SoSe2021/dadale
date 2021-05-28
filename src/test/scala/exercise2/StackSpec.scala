package exercise2

import org.scalatest.funspec.AnyFunSpec
import scala.util.Failure
import scala.util.Success


class StackSpec extends AnyFunSpec {
  describe("top") {
    it("returns the top element") {
      val a = new Stack(Option(1))
      assert(a.top.get == 1)
    }
  }

  describe("push") {
    it("adds a new element to the stack") {
      val a = new Stack(Option(1))
      val b = a.push(3)
      assert(b.top.get == 3)
    }
  }

  describe("pop") {
    it("removes the top element and returns the new top") {
      val a = new Stack(Option(1))
      val b = a.push(3)
      val c = b.pop
      assert(c.get.top.get == 1)
    }

    it("throws exception on empty stack") {
      val a = new Stack[Int](None, None)

      a.pop match {
        case Success(i) => assert(false)
        case Failure(s) => assert(true)
      }
    }
  }

  describe("reverse") {
    it("reverses all elements") {
      val a = new Stack(Option(1))
      val b = a.push(2).push(3).reverse
      assert(b.top.get == 1)
      assert(b.pop.get.top.get == 2)
      assert(b.pop.get.pop.get.top.get == 3)
    }
  }

  describe("isEmpty") {
    it("returns true if the last element gets popped") {
      val a = new Stack(Option(1))
      assert(a.isEmpty == false)
      val b = a.pop.get
      assert(b.isEmpty == true)
      val c = b.push(2)
      assert(c.isEmpty == false)
    }
  }
}
