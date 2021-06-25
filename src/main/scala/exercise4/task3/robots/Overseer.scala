package exercise4.task3

import zio._

class Overseer(val name: String) extends Robot {

  /** Whenever a job is completed, post about it on the News.
    * (including the name of the job and the robot who completed it)
    */
  override def work()
      : ZIO[Has[CompletedJobsHub] with Has[News], Nothing, Unit] = ???      
    // for {
    //   dequeue <- { CompletedJobsHub.subscribe }
    //   job <- dequeue.take
    //   _ <- News.submit(s"${job.completedBy.name} completed ${job.name}")
    // } yield ()
  
}


