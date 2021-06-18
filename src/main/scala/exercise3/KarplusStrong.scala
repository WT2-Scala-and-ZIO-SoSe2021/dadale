package exercise3

import exercise2._
import scala.util.Success
import scala.util.Failure
import exercise2.lib.StdAudio
import zio.{UIO, URIO, ZIO, App}
import zio.random._

object KarplusStrong extends App {

  def run(args: List[String]) =
    (for {
      noise <- whiteNoise()
      _ <- loop(noise)
    } yield ()).exitCode

  def play(sample: Double): UIO[Unit] = ZIO.effectTotal(StdAudio.play(sample))

  def whiteNoise(
      frequency: Int = 440,
      volume: Double = 1.0
  ): ZIO[Random, Throwable, Queue[Double]] = {
    if (frequency <= 0 || volume < 0.0 || volume > 1.0)
      return ZIO.fail(new Throwable("Incorrect parameters"))

    ZIO.foldLeft(0 to frequency)(new Queue[Double]()) { (acc, _) =>
      for {
        randomValue <- nextDoubleBetween(-0.5, 0.5)
        queue <- ZIO.effectTotal(acc.enqueue(randomValue * volume))
      } yield queue
    }
  }

  def update(queue: Queue[Double]): Option[Queue[Double]] = {
    val decay = 0.996

    queue
      .dequeue()
      .map(newQueue =>
        newQueue.enqueue((newQueue.front.get + queue.front.get) / 2 * decay)
      )
      .toOption
  }

  def loop(queue: Queue[Double]): ZIO[Random, Throwable, Unit] = {
    val updatedQueue = update(queue).get
    for {
      _ <- play(updatedQueue.front.get)
      _ <- loop(updatedQueue)
    } yield ()
  }
}
