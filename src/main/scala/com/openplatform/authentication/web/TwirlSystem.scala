package com.openplatform.authentication.web

import akka.actor.ActorSystem
import akka.io.IO
import com.openplatform.authentication.web.api.ReactiveApi
import spray.can.Http

object TwirlSystem extends App with ReactiveApi {

  implicit lazy val system = ActorSystem("AuthenticationSystem")
  sys.addShutdownHook({system.shutdown()})

  IO(Http) ! Http.Bind(rootService, Configuration.host, port = Configuration.portHttp)
}

object Configuration {
  import com.typesafe.config.ConfigFactory

  private val config = ConfigFactory.load
  config.checkValid(ConfigFactory.defaultReference)

  val host = config.getString("authentication.host")
  val portHttp = config.getInt("authentication.port")
}
