package exercise4.task3

import zio._
import zio.clock.Clock
import zio.console.Console

import zio.duration.durationInt
import zio.console._

class Reporter(val name: String) extends Robot {

  /** Whenever there are news, proclaims them (outputs the string to the console).
    * For simplicity, runs synchronously in the main fiber.
    */
  override def work(): ZIO[Has[News] with Clock with Console, Any, Unit] =
    action repeat Schedule.forever.unit

  val action = for {
    news <- News.proclaim()
    _ <- putStrLn(news)
  } yield ()

}
