import org.scalatest.funspec.AnyFunSpec;

class Exercise1Spec extends AnyFunSpec {
  val exercise1 = Main;
  describe("max") {
    it("should return the max value") {
      assert(exercise1.max(Array(12, 23, 3434, 23)).equals(3434))
    }
  }

  describe("min") {
    it("should return the min value") {
      assert(exercise1.min(Array(12, 23, 3434, 23)).equals(12))
    }
  }

  describe("sum") {
    it("should return the sum") {
      assert(exercise1.sum(Array(1, 2, 3)).equals(6))
    }
  }

  describe("parse") {
    it("should parse a picture card") {
      assert(exercise1.parse("Q").equals(10))
    }

    it("should parse a number card") {
      assert(exercise1.parse("7").equals(7))
    }

    it("should parse ace card") {
      assert(exercise1.parse("A").equals(11))
    }

    it("should parse multiple cards") {
      assert(
        exercise1.parseAll(Array("A", "7", "K")).sameElements(Array(11, 7, 10))
      )
    }
  }

  describe("values") {
    it("should replace ace values with both possible values") {
      assert(exercise1.values(11).sameElements(Array(1, 11)))
    }

    it("should keep value for different values than ace") {
      assert(exercise1.values(2).sameElements(Array(2)))
      assert(exercise1.values(10).sameElements(Array(10)))
    }
  }

  describe("isBust") {
    it("should return false for values lower or equal 21") {
      assert(exercise1.isBust(10) == false)
      assert(exercise1.isBust(21) == false)
    }

    it("should return true for values greater than 21") {
      assert(exercise1.isBust(22) == true)
    }
  }

  describe("determineHandValue") {
    it("should calculate correctly using an optimistic strategy") {
      assert(exercise1.optimisticF(Array(11,3,6,2)) == 22)
    }

    it("should calculate correctly using a pessimistic strategy") {
      assert(exercise1.pessimisticF(Array(11,3,6,2)) == 12)
    }
  }

  describe("simpleDetermineBestHandValue") {
    it("should choose the optimistic strategy if value is not busted") {
      assert(exercise1.determineBestHandValue(Array(11,3,6)) == 20)
    }

    it("should choose the pessimistic strategy if value is busted") {
      assert(exercise1.determineBestHandValue(Array(11,3,6,2)) == 12)
    }
  }

  describe("determineBestHandValue") {
    it("should choose the optimistic strategy if value is not busted") {
      assert(exercise1.determineBestHandValue(Array(11,3,6)) == 20)
    }

    it("should choose the pessimistic strategy if value is busted") {
      assert(exercise1.determineBestHandValue(Array(11,3,6,2)) == 12)
    }

    it("should pick the right values if multiple aces are provided") {
      assert(exercise1.determineBestHandValue(Array(2,3)) == 5)
      assert(exercise1.determineBestHandValue(Array(11,11,9)) == 21)
      assert(exercise1.determineBestHandValue(Array(11,11,11,8)) == 21)
    }
  }

}