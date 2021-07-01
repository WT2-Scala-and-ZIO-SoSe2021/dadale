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
  ] with Clock with Random with Console, Nothing, Unit] = action.forever

  val action = for {
    job <- JobBoard.take()
    _ <- ZIO.sleep(job.duration)
    random <- nextDoubleBetween(-0, 50)
    _ <- putStrLn(s"random: $random")
    _ <- ZIO.when(random <= 10) {
      for {
        _ <- putStrLn("In error")
        _ <- JobBoard.submit(job)
        _ <- ZIO.sleep(Duration.apply(2, TimeUnit.SECONDS))
      } yield ()
    }
    _ <- ZIO.when(random > 10) {
      for {
        _ <- putStrLn("In success")
        _ <- CompletedJobsHub.publish(CompletedJob(job.name, this))
      } yield ()
    }
  } yield ()
}
