package exercise2

import org.scalatest.funspec.AnyFunSpec

class QueueSpec extends AnyFunSpec {
  describe("front") {
    it("returns the first element in the queue") {
      val a = new Queue[Int]()
      val b = a.enqueue(1)
      assert(b.front.get == 1)
    }
  }

  describe("enqueue and dequeue") {
    it("enqueue adds element to queue, dequeue removes front element") {
      val a = new Queue[Int]().enqueue(1).enqueue(2).enqueue(3)
      assert(a.front.get == 1)
      val b = a.dequeue.get
      assert(b.front.get == 2)
      val c = b.dequeue.get
      assert(c.front.get == 3)
    }

    it("dequeue on empty queue throws exception") {
      val a = new Queue[Int]()
      val caught = intercept[Throwable] {
        val b = a.dequeue.get
      }
      assert(caught.getMessage == "Queue is empty")
    }
  }

  describe("isEmpty") {
    it("returns true if queue is empty") {
      val a = new Queue[Int]().enqueue(2)
      val b = a.dequeue.get
      assert(b.isEmpty)
    }
  }
}
