package exercise4.task3

import zio._

class Praiser(val name: String) extends Robot {

  /** Run whenever a job is completed.
    * Posts a nice comment about the robot who completed it on the news.
    * (including only the name of the robot, who completed it)
    */
  override def work(): ZIO[Has[CompletedJobsHub] with Has[News], Any, Unit] = CompletedJobsHub.subscribe.use { 
    queue => (for {
        job <- queue.take
        _ <- News.post(s"well done ${job.completedBy.name}")
      } yield ()).forever
    }

}
