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

case class JobBoardLive(queue: Queue[PendingJob]) extends JobBoard {

  override def submit(job: PendingJob): UIO[Unit] = queue.offer(job).unit

  override def take(): UIO[PendingJob] = queue.take
}

object JobBoard {
  def submit(job: PendingJob): URIO[Has[JobBoard], Unit] =
    ZIO.serviceWith[JobBoard](_.submit(job))

  def take(): URIO[Has[JobBoard], PendingJob] =
    ZIO.serviceWith[JobBoard](_.take())
}

object JobBoardLive {
  val layer: ULayer[Has[JobBoard]] = {
    val jobBoard = for {
      queue <- Queue.unbounded[PendingJob]
      jobBoard = JobBoardLive(queue)
    } yield jobBoard
    ZLayer.fromEffect(jobBoard)
  }
}
