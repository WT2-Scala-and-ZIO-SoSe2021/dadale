// in file gardening/fruits/package.scala
package exercise4

import zio._
import zio.clock.Clock

package object task3 {
  type MyEnv = Has[CompletedJobsHub]
    with Has[JobBoard]
    with Has[News]
    with Clock

  trait Robot {
    val name: String
    def work(): ZIO[MyEnv, Any, Unit]
  }

  sealed trait Job {}

  case class PendingJob(duration: zio.duration.Duration) extends Job
  case class CompletedJob(completedBy: Robot) extends Job
}
