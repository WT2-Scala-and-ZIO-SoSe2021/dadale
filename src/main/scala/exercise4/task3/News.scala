package exercise4.task3

import zio._

trait News {
  def post(news: String): UIO[Unit]

  def proclaim(): UIO[String]
}

case class NewsLive(queue: Queue[String]) extends News {

  override def post(news: String): UIO[Unit] = queue.offer(news).unit

  override def proclaim(): UIO[String] = queue.take
}

object News {
  def post(news: String): URIO[Has[News], Unit] =
    ZIO.serviceWith[News](_.post(news))

  def proclaim(): URIO[Has[News], String] =
    ZIO.serviceWith[News](_.proclaim())
}

object NewsLive {
  val layer: ULayer[Has[News]] = {
    val news = for {
      queue <- Queue.unbounded[String]
      news = NewsLive(queue)
    } yield news
    ZLayer.fromEffect(news)
  }
}
