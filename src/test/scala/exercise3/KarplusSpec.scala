package exercise3

import zio._
import zio.test._
import zio.test.Assertion._
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
      } yield  assert(queue.front()) (isSome)
    },

    testM("Queue value should be between -0.5 and 0.5") {
      val volume = .5
      for {
        queue <- whiteNoise(2, volume)
      } yield assert(queue.front().get)(isLessThan(0.5 * volume) && isGreaterThan(-0.5 * volume))
    },

    // TODO: test throw exception
    testM("Throws exception") {
      val volume = 3.0
      for {
        queue <- whiteNoise(2, volume)
        // https://stackoverflow.com/questions/58662285/how-to-test-an-exception-case-with-zio-test
        result <- ZIO.fail("Incorrect parameters")
      } yield assert(result)(fails(equalTo("Incorrect parameters")))
      //assert(queue)(fails(equalTo("Incorrect parameters")))
    }

  )

}
