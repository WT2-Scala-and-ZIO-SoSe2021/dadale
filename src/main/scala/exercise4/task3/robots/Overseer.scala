package exercise4.task3

import zio._

class Overseer(val name: String) extends Robot {

  /** Whenever a job is completed, post about it on the News.
    * (including the name of the job and the robot who completed it)
    */
  override def work()
      : ZIO[Has[CompletedJobsHub] with Has[News], Nothing, Unit] = CompletedJobsHub.subscribe.use { 
    queue => (for {
        job <- queue.take
        _ <- News.post(s"${job.completedBy.name} completed ${job.name}")
      } yield ()).forever
    }
  
}


