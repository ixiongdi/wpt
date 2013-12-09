package net.imiui.wpt.util

import com.typesafe.config._
import org.fusesource.scalate._

abstract class BaseRoute {
  // val conf = ConfigFactory.load("config/wxcms.conf")
  // val dir = System.getProperty("user.dir") + "/src/main/scala/views"
  // val templateDirs = List(new java.io.File(dir))
  // val scalateMode = "production"
  // implicit val engine = new TemplateEngine(templateDirs, scalateMode)
  // implicit val bindings: List[Binding] = List(Binding(name = "foo", className = "String"))
  // implicit val additionalAttributes = List(("foo", "bar"))
}

trait Scalate {
  val templateDirs = List(new java.io.File("../views"))
  val scalateMode = com.twitter.finatra.config.env()
  val engine = new TemplateEngine(templateDirs, scalateMode)

  def scalate(template: String, args: Map[String, Any]) = {
    val source = TemplateSource.fromFile(template)
    val output = engine.layout(source, args)
    output
  }
}

import com.twitter.finagle.{ Service, SimpleFilter }
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer
import com.twitter.util.Future
import com.twitter.finagle.http.{ Request => FinagleRequest, Response => FinagleResponse }

import org.apache.commons.io.IOUtils
import java.io.{ FileInputStream, File, InputStream }
import javax.activation.MimetypesFileTypeMap
import com.twitter.app.App
import com.twitter.finatra.FileService

object FileResolver {

  def hasFile(path: String): Boolean = {
    hasResourceFile(path)
  }

  def getInputStream(path: String): InputStream = {
    getResourceInputStream(path)
  }

  private def getResourceInputStream(path: String): InputStream =
    getClass.getResourceAsStream(path)

  private def hasResourceFile(path: String): Boolean = {
    val fi = getClass.getResourceAsStream(path)
    var result = false

    try {
      if (fi != null && fi.available > 0) {
        result = true
      } else {
        result = false
      }
    } catch {
      case e: Exception =>
        result = false
    }
    result
  }
}

class FileService(parent: String) extends com.twitter.finatra.FileService {

  override def apply(request: FinagleRequest, service: Service[FinagleRequest, FinagleResponse]): Future[FinagleResponse] = {
    val path = new File(parent, request.path).toString
    println(path)

    if (FileResolver.hasFile(path) && request.path != "/") {
      val fh = FileResolver.getInputStream(path)
      val b = IOUtils.toByteArray(fh)

      fh.read(b)

      val response = request.response
      val mtype = FileService.extMap.getContentType('.' + request.path.toString.split('.').last)

      response.status = OK
      response.setHeader("Content-Type", mtype)
      response.setContent(copiedBuffer(b))

      Future.value(response)
    } else {
      service(request)
    }
  }
}

object Helper {
  val s = ""
}