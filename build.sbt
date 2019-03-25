name := "GOF_Patterns"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-lang3" % "3.8.1",  // used by builder
  "com.google.inject" % "guice" % "4.2.2", // used by bridge
  "javassist" % "javassist" % "3.12.1.GA",  // used by advanced proxy
  "org.springframework.statemachine" % "spring-statemachine-core" % "2.1.0.RELEASE", //state
  "commons-codec" % "commons-codec" % "1.11" //scalaBridge
)
