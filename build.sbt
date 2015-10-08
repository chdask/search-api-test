name := "nap-scala-test"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val sprayV = "1.3.2"
  val akkaV = "2.3.6"
  Seq(
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "io.spray"            %%  "spray-can"     % sprayV withSources() withJavadoc(),
    "io.spray"            %%  "spray-routing" % sprayV withSources() withJavadoc(),
    "io.spray"            %%  "spray-json"    % "1.3.1",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
//    "io.spray"            %% "spray-caching"  % "1.3.1",
    "org.json4s"          %% "json4s-jackson" % "3.2.11",
    "ch.qos.logback"      %  "logback-classic" % "1.1.2",
    "org.scalaj"          %% "scalaj-http"    % "1.1.5",
    "com.gettyimages"     %% "spray-swagger"  % "0.5.1",
    "org.scalatest"       %% "scalatest"      % "2.2.4" % "test"
  )
}

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io"
)

oneJarSettings

mainClass in oneJar := Some("com.nap.search.BootServer")