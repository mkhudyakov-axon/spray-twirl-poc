resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray repository" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  val akkaV = "2.2.3"
  val sprayV = "1.2.2"
  Seq(
    "com.typesafe.akka"   %%  "akka-actor"     % akkaV,
    "io.spray"            %%  "spray-json"     % "1.2.5",
    "io.spray"            %   "spray-can"      % sprayV,
    "io.spray"            %   "spray-routing"  % sprayV
  )
}

Twirl.settings