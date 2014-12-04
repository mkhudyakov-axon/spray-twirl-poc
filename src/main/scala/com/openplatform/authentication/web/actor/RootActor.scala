package com.openplatform.authentication.web.actor

import akka.actor.{ActorLogging, Actor}
import spray.routing._

/**
 * @author Maksym Khudiakov
 */
class RootActor(route : Route) extends Actor with HttpService with ActorLogging {
  implicit def actorRefFactory = context

  def receive = runRoute(route)
}
