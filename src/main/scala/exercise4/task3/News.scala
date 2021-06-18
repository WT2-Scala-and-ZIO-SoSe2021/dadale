package exercise4.task3

import zio._

trait News {
  def post(news: String): UIO[Unit]

  def proclaim(): UIO[String]
}

case class NewsLive() extends News {
  val unboundedQueue: UIO[Queue[String]] = Queue.unbounded[String]

  override def post(news: String): UIO[Unit] = for {
    queue <- unboundedQueue
    _ <- queue.offer(news)
  } yield ()

  override def proclaim(): UIO[String] = for {
    queue <- unboundedQueue
    option <- queue.poll
  } yield option.getOrElse("")
}

object News {
  def submit(news: String): URIO[Has[News], Unit] =
    ZIO.serviceWith[News](_.post(news))

  def take(): URIO[Has[News], String] =
    ZIO.serviceWith[News](_.proclaim())
}

object NewsLive {
  val layer: ULayer[Has[News]] = ZLayer.succeed(NewsLive())
}
