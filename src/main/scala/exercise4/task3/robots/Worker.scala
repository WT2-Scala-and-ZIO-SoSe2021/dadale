package exercise4.task3

import zio._
import zio.clock.Clock
import zio.duration.durationInt
import zio.random._
import zio.console._
import zio.duration.Duration
import java.util.concurrent.TimeUnit

class Worker(val name: String) extends Robot {

  /** Takes jobs from the JobBoard, execute them and once completed.
    * Sleeps for the duration of the job before taking the next job.
    * Publishes them on the CompletedJobsHub.
    */
  override def work(): ZIO[Has[JobBoard] with Has[
    CompletedJobsHub
  ] with Clock with Random with Console, java.io.IOException, Unit] =
    action.forever

  val action = for {
    job <- JobBoard.take()
    _ <- ZIO.sleep(job.duration)
    random <- nextDouble
    _ <- ZIO.when(random <= 0.2) {
      for {
        _ <- putStrLn(s"$name had an error")
        _ <- JobBoard.submit(job)
        _ <- ZIO.sleep(Duration.apply(10, TimeUnit.SECONDS))
        _ <- putStrLn(s"$name rebooted")
      } yield ()
    }
    _ <- ZIO.when(random > 0.2) {
      for {
        _ <- CompletedJobsHub.publish(CompletedJob(job.name, this))
      } yield ()
    }
  } yield ()
}
