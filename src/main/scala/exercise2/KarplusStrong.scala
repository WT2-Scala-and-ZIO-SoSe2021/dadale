package exercise2

import exercise2.lib.StdAudio
import scala.util.Random
import scala.annotation.tailrec
import scala.util.Failure
import scala.util.Success

object KarplusStrong {
  def main(args: Array[String]): Unit = {
    loop(StdAudio.play)(whiteNoise())
  }

  def whiteNoise(frequency: Int = 440, volume: Double = 1.0): Queue[Double] = {
    if (frequency <= 0 && volume < 0.0 && volume > 1.0)
      throw new Throwable("Incorrect parameters")

    (0 to frequency).foldLeft(new Queue[Double]()) { (acc, _) =>
      acc
        .enqueue(Random.between(-0.5, 0.5) * volume)
    }
  }

  def update(queue: Queue[Double]): Option[Queue[Double]] = {
    val decay = 0.996
    
    queue.dequeue match {
      case Success(newQueue) => Some(newQueue.enqueue((newQueue.front.get + queue.front.get) / 2 * decay))
      case Failure (_) => None
    }
  }

  @tailrec
  def loop(f: Double => Unit)(queue: Queue[Double]): Unit = {
    val updatedQueue = update(queue).get
    f(updatedQueue.front.get)
    loop(f)(updatedQueue)
  }
}
