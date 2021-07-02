package exercise4.task3

import zio._
import zio.clock.Clock
import zio.duration.durationInt
import zio.duration.Duration

object Task3 extends zio.App {

  val elder1 = new Elder("Elder1")
  val elder2 = new Elder("Elder2")

  val worker1 = new Worker("Worker1")
  val worker2 = new Worker("Worker2")

  val overseer = new Overseer("Overseer")
  val praiser = new Praiser("Praiser")
  val reporter = new Reporter("Reporter")

  def f() = for {
    _ <- overseer.work().fork
    _ <- worker1.work().fork
    _ <- worker2.work().fork
    _ <- ZIO.sleep(1.second)
    _ <- elder1.work().fork
    _ <- elder2.work().fork
    _ <- praiser.work().fork
    _ <- reporter.work()
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = f()
    .provideCustomLayer(
      JobBoardLive.layer ++ CompletedJobsHubLive.layer ++ NewsLive.layer
    )
    .exitCode
}
