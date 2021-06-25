package exercise4.task3

import zio.duration.durationInt
import zio._
import zio.clock.Clock

class Elder(val name: String) extends Robot {

  /** Publishes jobs to the JobBoard at specific intervals.
    */
  override def work(): ZIO[Has[JobBoard] with Clock, Nothing, Unit] =
    JobBoard.submit(
      PendingJob("Job name", 10.second) // TODO ask Anton about job name, maybe generate a random
    ) repeat Schedule.spaced(5.second).unit
}
