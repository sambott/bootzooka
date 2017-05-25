addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.2")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.6.1")

addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.0")

addSbtPlugin("com.updateimpact" % "updateimpact-sbt-plugin" % "2.1.1")

addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "0.6.8")

resolvers += Resolver.bintrayIvyRepo("kamon-io", "sbt-plugins")

addSbtPlugin("io.kamon" % "sbt-aspectj-play-runner" % "1.0.1")
