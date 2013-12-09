package net.imiui.wpt.controllers

import com.twitter.finatra._

import net.imiui.wpt.models.HelloModel
import net.imiui.wpt.views.HelloView
import net.imiui.wpt.util.Scalate

object HelloController extends Controller with Scalate {

  get("/hello/:name") { request =>
    val name = request.routeParams("name")
    render.plain("hello " + name).toFuture
  }

  get("/") { request =>
    render.plain("pong").toFuture
  }

  get("/index") { request =>
    render.html(scalate("hello.ssp", Map("name" -> "1", "city" -> "2"))).toFuture
  }

  get("/say/:word") { request =>
    val word = request.routeParams("word")
    render.plain(HelloModel.say(word)).toFuture
  }

}