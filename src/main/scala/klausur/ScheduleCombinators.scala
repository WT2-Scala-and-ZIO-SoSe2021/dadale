package klausur

import zio.clock.Clock
import zio.duration.durationInt
import zio.duration.Duration
import zio._
import zio.console.Console
import zio.console._

import zio.duration.durationInt
import zio._
import zio.clock.Clock

object ScheduleCombinators extends zio.App {

  val action1 = for {
    _ <- putStrLn("1")
  } yield ()

  val action2 = for {
    _ <- putStrLn("2")
  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val sequential = Schedule.recurs(10) andThen Schedule.spaced(1.second)
    (action1 repeat sequential).exitCode
  }
}
