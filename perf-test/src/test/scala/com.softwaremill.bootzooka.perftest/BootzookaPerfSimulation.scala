package com.softwaremill.bootzooka.perftest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

class BootzookaPerfSimulation extends Simulation {

  val baseURL = "http://localhost:8080/api"

  val httpConf = http.baseURL(baseURL).contentTypeHeader(HttpHeaderValues.ApplicationJson)

  val userJson =
    """
      |{
      |  "login": "${login}",
      |  "password": "very secret password",
      |  "email": "${login}@nonexistingmailserver.com"
      |}
    """.stripMargin

  def feeder = Iterator.continually(Map("login" -> randomLogin))

  val getVersionScenario = scenario("Get version")
    .exec(
      http("get version")
      .get("/version"))

  def randomLogin = Random.alphanumeric.take(10 + Random.nextInt(10)).mkString

  val registerUserScenario = scenario("Register user")
    .feed(feeder)
    .exec(
      http("register user")
      .post("/users/register").body(StringBody(userJson)))


  setUp(
    getVersionScenario
      .inject(atOnceUsers(3000))
      .protocols(httpConf),
    registerUserScenario
      .inject(atOnceUsers(3000))
      .protocols(httpConf))
    .assertions(forAll.failedRequests.percent.lt(10))

}



