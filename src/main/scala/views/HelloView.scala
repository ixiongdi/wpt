package net.imiui.wpt.views

import com.twitter.finatra._
import org.fusesource.scalate._

object HelloView {
  def apply(template: String, args: Map[String, Any]) = {
    val templateDirs = List(new java.io.File("."))
    val scalateMode = "development"
    println(templateDirs)
    val engine = new TemplateEngine(templateDirs, scalateMode)
    val source = TemplateSource.fromFile(template)
    val output = engine.layout(source, args)
    // override def toString(): String =  output 
    output
  }

}