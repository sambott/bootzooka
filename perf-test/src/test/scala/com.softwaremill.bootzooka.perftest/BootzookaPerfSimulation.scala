package com.softwaremill.bootzooka.perftest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BootzookaPerfSimulation extends Simulation {

  val baseURL = "http://localhost:8080/api"

  val httpConf = http.baseURL(baseURL)

  val scn = scenario("Get version")
    .exec(http("get version")
    .get("/version"))

  setUp(scn
    .inject(atOnceUsers(300))
    .protocols(httpConf))

}

