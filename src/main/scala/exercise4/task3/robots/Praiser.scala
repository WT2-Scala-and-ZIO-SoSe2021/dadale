package exercise4.task3

import zio.ZIO

class Praiser(val name: String) extends Robot {

  /** Run whenever a job is completed.
    * Posts a nice comment about the robot who completed it on the news.
    * (including only the name of the robot, who completed it)
    */
  override def work(): ZIO[MyEnv, Any, Unit] = ???
//   for {
//     dequeue <- { CompletedJobsHub.subscribe }
//     job <- dequeue.take
//     _ <- News.submit(s"well done ${job.completedBy.name}")
//   } yield ()

}
