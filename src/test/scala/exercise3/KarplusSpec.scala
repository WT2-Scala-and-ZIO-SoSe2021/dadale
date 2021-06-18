package exercise3

import zio._
import zio.test._
import zio.test.Assertion._
import zio.test.environment.TestRandom
import exercise3.KarplusStrong._

object KarplusSpec extends DefaultRunnableSpec {

  def spec = suite("KarplusSpec whiteNoise")(
    testM("Queue shouldn't be empty by default") {
      for {
        queue <- whiteNoise()
      } yield assert(queue.isEmpty)(equalTo(false))
    },
    testM("Queue-Element is instance of Some") {
      for {
        queue <- whiteNoise()
      } yield assert(queue.front())(isSome)
    },
    testM("Queue value should be between -0.5 and 0.5") {
      val randomDoubles = List(0.5, 0.2, 0.3)
      val expectedRandoms = List(0.25, 0.1, 0.15)
      for {
        _ <- TestRandom.feedDoubles(
          randomDoubles(0),
          randomDoubles(1),
          randomDoubles(2)
        )
        queue <- whiteNoise(3, 0.5)
      } yield assert(expectedRandoms)(
        equalTo(
          List(
            queue.front().get,
            queue.dequeue().get.front().get,
            queue.dequeue().get.dequeue().get.front().get
          )
        )
      )
    }

    // To test exceptions in the whiteNoise function, it should return: ZIO[Random, IllegalArgumentException, Queue[Double]]
    // Example:
    //        testM("throws a runtime exception") {
    //          for {
    //            res <- ZIO.succeed(throw new IllegalArgumentException("unexpected error")).run
    //          } yield assert(res)(dies(isSubtype[IllegalArgumentException](anything)))
    //        }
  )

}
