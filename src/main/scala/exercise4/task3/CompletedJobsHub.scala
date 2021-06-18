package exercise4.task3
import zio._

trait CompletedJobsHub {
  def subscribe: ZManaged[Any, Nothing, Dequeue[CompletedJob]]

  def publish(job: CompletedJob): UIO[Unit]
}

case class CompletedJobsHubLive() extends CompletedJobsHub {
  val unboundedHub: UIO[Hub[CompletedJob]] = Hub.unbounded[CompletedJob]

  override def subscribe: ZManaged[Any, Nothing, Dequeue[CompletedJob]] = for {
    hub <- unboundedHub
    subscribeHub <- hub.subscribe
  } yield subscribeHub

  override def publish(job: CompletedJob): UIO[Unit] = for {
    hub <- unboundedHub
    _ <- hub.publish(job)
  } yield ()
}

object CompletedJobsHub {
  def subscribe: URIO[Has[CompletedJobsHub], ZManaged[Any, Nothing, Dequeue[
    CompletedJob
  ]]] =
    ZIO.serviceWith[CompletedJobsHub](_.subscribe)

  def publish(job: CompletedJob): URIO[Has[CompletedJobsHub], Unit] =
    ZIO.serviceWith[CompletedJobsHub](_.publish(job))
}

object CompletedJobsHubLive {
  val layer: ULayer[Has[CompletedJobsHub]] =
    ZLayer.succeed(CompletedJobsHubLive())
}
