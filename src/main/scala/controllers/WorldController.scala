package net.imiui.wpt.controllers

import com.twitter.finatra._

import net.imiui.wpt.models.HelloModel

object WorldController extends Controller {
  get("/world") { request =>
    render.json(Map("hello" -> "world")).toFuture
  }

  get("/static") { request =>
    render.static("WorldController.scala").toFuture
  }
}