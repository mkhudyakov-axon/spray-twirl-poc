package com.openplatform.authentication.web.api

import akka.actor.{Props, ActorSystem}
import akka.event.Logging._
import com.openplatform.authentication.web.actor.RootActor
import spray.http.HttpRequest
import spray.http.StatusCodes._
import spray.httpx.TwirlSupport
import spray.routing.directives.LogEntry
import spray.routing.{RouteConcatenation, Directives}

/**
 * @author Maksym Khudiakov
 */
trait AbstractSystem {
  implicit def system: ActorSystem
}

trait ReactiveApi extends RouteConcatenation with StaticRoute with AbstractSystem {

  lazy val routes = logRequest(showReq _) {
      staticRoute
  }

  private def showReq(req : HttpRequest) = {
    LogEntry(req.uri, InfoLevel)
  }

  val rootService = system.actorOf(Props(classOf[RootActor], routes))
}

trait StaticRoute extends Directives with TwirlSupport {
  this: AbstractSystem =>

  lazy val staticRoute =
    path("") {
      get {
        complete(html.login("login", "password"))
      }
    } ~ complete(NotFound)
}
