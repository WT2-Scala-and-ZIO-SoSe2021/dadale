// in file gardening/fruits/package.scala
package exercise4

import zio._

package object task3 {
  type MyEnv = zio.ZEnv

  trait Robot {
    val name: String
    def work(): ZIO[MyEnv, Any, Unit]
  }

  sealed trait Job {}

  case class PendingJob(duration: zio.duration.Duration) extends Job
  case class CompletedJob(completedBy: Robot) extends Job
}
