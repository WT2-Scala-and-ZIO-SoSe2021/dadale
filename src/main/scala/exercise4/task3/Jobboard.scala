package exercise4.task3

import zio._

trait JobBoard {

  /**  Submits a job to the job board, which can later be taken up by a robot using the take method.
    */
  def submit(job: PendingJob): UIO[Unit]

  /**  Take a job from the job board
    */
  def take(): UIO[PendingJob]
}

case class JobBoardLive() extends JobBoard {
  val unboundedQueue: UIO[Queue[PendingJob]] = Queue.unbounded[PendingJob]

  override def submit(job: PendingJob): UIO[Unit] = for {
    queue <- unboundedQueue
    _ <- queue.offer(job)
  } yield ()

  override def take(): UIO[PendingJob] = for {
    queue <- unboundedQueue
    job <- queue.take
  } yield job
}

object JobBoard {
  def submit(job: PendingJob): URIO[Has[JobBoard], Unit] =
    ZIO.serviceWith[JobBoard](_.submit(job))

  def take(): URIO[Has[JobBoard], PendingJob] =
    ZIO.serviceWith[JobBoard](_.take())
}

object JobBoardLive {
  val layer: ULayer[Has[JobBoard]] = ZLayer.succeed(JobBoardLive())
}
