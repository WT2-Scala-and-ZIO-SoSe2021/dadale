package klausur

import zio._

object ZLayers extends zio.App {

  trait Config
  trait Logging
  trait Parsing
  trait Database
  trait Serialization
  trait UserService

  val configLive: ULayer[Has[Config]] = ???
  val userServiceLive: URLayer[Has[Database] with Has[Logging] with Has[
    Serialization
  ], Has[UserService]] = ???
  val parsingLive: ULayer[Has[Parsing]] = ???
  val serializationLive: URLayer[Has[Parsing], Has[Serialization]] = ???
  val databaseLive: URLayer[Has[Config], Has[Database]] = ???
  val loggingLive: ULayer[Has[Logging]] = ???

  type MyEnv = Has[Database]
    with Has[Logging]
    with Has[Serialization]
    with Has[UserService]
    with Has[Parsing]
    with Has[Config]

  def f(): URIO[MyEnv, Unit] = ???

  val dbOut = (configLive >+> databaseLive)
  val dbLogParsOut = dbOut ++ loggingLive ++ parsingLive
  val dbLogParsSerOut = dbLogParsOut >+> serializationLive
  val dbLogParsSerUserOut = dbLogParsSerOut >+> userServiceLive

  val tmp =
    (configLive >+> databaseLive) ++ loggingLive ++ parsingLive >+> serializationLive >+> userServiceLive

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = f()
    .provideCustomLayer(
      dbLogParsSerUserOut
    )
    .exitCode
}


