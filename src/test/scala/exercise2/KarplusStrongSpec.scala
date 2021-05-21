package exercise2

import org.scalatest.funspec.AnyFunSpec

class KarplusStrongSpec extends AnyFunSpec {
  describe("whiteNoise") {
    it("returns a queue that fits the given parameters") {
      val volume = .5
      val queue = KarplusStrong.whiteNoise(2, volume)

      assert(queue.front().isInstanceOf[Some[Double]])
      assert(queue.front().get < 0.5 * volume && queue.front().get > - 0.5 * volume)
    }
  } 

  describe("update") {
    it("returns a queue") {
      val queue = KarplusStrong.whiteNoise(40, 1.0)
      val newQueue = KarplusStrong.update(queue)

      assert(queue != newQueue)
    }
  } 
}
