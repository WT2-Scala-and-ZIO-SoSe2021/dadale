name := "dadale"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.5" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.5" % "test"

libraryDependencies += "dev.zio" %% "zio" % "1.0.8"
libraryDependencies += "dev.zio" %% "zio-streams" % "1.0.8"
libraryDependencies +=  "dev.zio" %% "zio-test" % "1.0.8"


//libraryDependencies ++= Seq(
//  "dev.zio" %% "zio-test"          % zioVersion % "test",
//  "dev.zio" %% "zio-test-sbt"      % zioVersion % "test"
//)
testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")