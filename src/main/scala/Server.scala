package net.imiui.wpt

import com.twitter.finatra._

import net.imiui.wpt.controllers._
import net.imiui.wpt.utils.FileService

object App extends FinatraServer {
  // val urls = getClass().getClassLoader().getResources("META-INF/resources/webjars/")
  // val us = scala.collection.mutable.Set[java.net.URL]()
  // while (urls.hasMoreElements) {
  //   us += urls.nextElement
  // }
  // for (u <- us) {
  //   addFilter(new FileService(u.toString))
  // }
  addFilter(new FileService("/META-INF/resources/webjars/bootstrap/3.0.1/css"))
  register(HelloController)
  register(WorldController)
}
