package exercise3

import zio.console._
import exercise2._
import scala.util.Success
import scala.util.Failure
import scala.annotation.tailrec
import exercise2.lib.StdAudio
import zio.{IO, UIO, URIO, ZIO, App}
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
  ): URIO[Random, Queue[Double]] = {
    if (frequency <= 0 && volume < 0.0 && volume > 1.0)
      throw new Throwable("Incorrect parameters")

    ZIO.foldLeft(0 to frequency)(new Queue[Double]()) { (acc, _) =>
      for {
        randomValue <- nextDoubleBetween(-0.5, 0.5)
        queue <- ZIO.effectTotal(acc.enqueue(randomValue * volume))
      } yield queue
    }
  }

  def update(queue: Queue[Double]): Option[Queue[Double]] = {
    val decay = 0.996

    queue.dequeue match {
      case Success(newQueue) =>
        Some(
          newQueue.enqueue((newQueue.front.get + queue.front.get) / 2 * decay)
        )
      case Failure(_) => None
    }
  }

  def loop(queue: Queue[Double]): ZIO[Random, Throwable, Unit] = {
    val updatedQueue = update(queue).get
    for {
      _ <- play(updatedQueue.front.get)
      _ <- loop(updatedQueue)
    } yield ()
  }
}
