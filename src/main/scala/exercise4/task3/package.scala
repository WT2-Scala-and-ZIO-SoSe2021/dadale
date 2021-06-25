package exercise4

import zio._
import zio.clock.Clock
import zio.console.Console


package object task3 {
  type MyEnv = Has[CompletedJobsHub]
    with Has[JobBoard]
    with Has[News]
    with Clock
    with Console

  trait Robot {
    val name: String
    def work(): ZIO[MyEnv, Any, Unit]
  }

  sealed trait Job {
    val name: String
  }

  case class PendingJob(name: String, duration: zio.duration.Duration) extends Job
  case class CompletedJob(name: String, completedBy: Robot) extends Job
}
