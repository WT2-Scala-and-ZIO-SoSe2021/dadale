package exercise2

import org.scalatest.funspec.AnyFunSpec

class IorSpec extends AnyFunSpec {
  describe("Ior.right") {
    it("should create case class") {
      assert(Ior.right(2) == Right(2))
      assert(Ior.right("a string") == Right("a string"))
    }
  }

  describe("Ior.unit") {
    it("should create Right case class") {
      assert(Ior.unit(2) == Right(2))
      assert(Ior.unit("a string") == Right("a string"))
    }
  }

  describe("map") {
    it("should map the value and return Right") {
      val a = Ior.right(2)
      assert(a.map(x => x * 4) == Right(8))
    }

    it("should be able to change the type") {
      val a = Ior.right(2)
      assert(a.map(x => x.toString()) == Right("2"))
    }
  }

  describe("flatMap") {
    it("should change on Right") {
      val a = Ior.right(2)
      assert(a.flatMap(_ => Ior.right("a string")) == Right("a string"))
    }

    it("should not change on Left") {
      val a = Ior.left(new RuntimeException("a grave error"))
      assert(a.flatMap(_ => Ior.right("a string")) != Right("a string"))
    }

    it("should change element on Both") {
      val a = Ior.both(new RuntimeException("not fatal"), 2)
      val b = a.flatMap(x => Ior.right(x * 2))
      val c = b match {
        case Both(left, elem) => Both(left, elem)
        case _ => {
          assert(false)
          Both(new RuntimeException(""), "")
        }
      }
      assert(a.left.getMessage() == c.left.getMessage())
      assert(c.elem == 4)
    }

    it("should change left on Both and return Left") {
      val a = Ior.both(new RuntimeException("not fatal"), 2)
      val b = a.flatMap(_ => Ior.left[Int](new RuntimeException("fatal error")))
      val c = b match {
        case Left(left) => Left(left)
        case _ => {
          assert(false)
          Left(new RuntimeException(""))
        }
      }
      assert(a.left.getMessage() != c.elem.getMessage())
    }

    it("should change element and left on Both") {
      val a = Ior.both(new RuntimeException("not fatal"), 2)
      val b =
        a.flatMap(x => Ior.both(new RuntimeException("fatal error"), x * 2))
      val c = b match {
        case Both(left, elem) => Both(left, elem)
        case _ => {
          assert(false)
          Both(new RuntimeException(""), "")
        }
      }
      assert(a.left.getMessage() != c.left.getMessage())
      assert(c.elem == 4)
    }
  }
}
