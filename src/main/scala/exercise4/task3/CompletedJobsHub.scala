package exercise4.task3
import zio._

trait CompletedJobsHub {
  def subscribe: ZManaged[Any, Nothing, Dequeue[CompletedJob]]

  def publish(job: CompletedJob): UIO[Unit]
}

case class CompletedJobsHubLive(hub: Hub[CompletedJob])
    extends CompletedJobsHub {

  override def subscribe: ZManaged[Any, Nothing, Dequeue[CompletedJob]] =
    hub.subscribe

  override def publish(job: CompletedJob): UIO[Unit] = hub.publish(job).unit
}

object CompletedJobsHub {
  def subscribe: ZManaged[Has[CompletedJobsHub], Nothing, Dequeue[
    CompletedJob
  ]] = Managed.accessManaged[Has[CompletedJobsHub]](_.get.subscribe)

  def publish(job: CompletedJob): URIO[Has[CompletedJobsHub], Unit] =
    ZIO.serviceWith[CompletedJobsHub](_.publish(job))
}

object CompletedJobsHubLive {
  val layer: ULayer[Has[CompletedJobsHub]] = {
    val jobsHub = for {
      hub <- Hub.unbounded[CompletedJob]
      jobsHub = CompletedJobsHubLive(hub)
    } yield jobsHub
    ZLayer.fromEffect(jobsHub)
  }
}
