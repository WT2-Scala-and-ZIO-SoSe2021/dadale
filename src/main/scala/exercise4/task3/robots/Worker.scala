package exercise4.task3

import zio._
import zio.clock.Clock
import zio.duration.durationInt

class Worker(val name: String) extends Robot {

  /** Takes jobs from the JobBoard, execute them and once completed.
    * Publishes them on the CompletedJobsHub.
    * Sleeps for duration the duration of the job before taking the next job.
    */
  override def work(): ZIO[Has[JobBoard] with Has[
    CompletedJobsHub
  ] with Clock, Nothing, Unit] = action repeat Schedule.forever.unit

  val action = for {
    job <- JobBoard.take()
    _ <- ZIO.sleep(job.duration)
    _ <- CompletedJobsHub.publish(CompletedJob(this))
  } yield ()
}
