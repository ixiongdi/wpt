import ScalateKeys._

seq(scalateSettings:_*)

scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) { base =>
  Seq(
    TemplateConfig(
      base / "scala" / "views",
      Seq("import net.imiui.wpt.util.Helper._"),
      Seq(Binding("env", "com.twitter.finatra.config.env()")),
      Some("web")
    )
  )
}

seq(sbtavro.SbtAvro.avroSettings : _*)

(stringType in avroConfig) := "String"

seq(Revolver.settings: _*)

mainClass in Revolver.reStart := Some("net.imiui.wpt.App")

seq(jsSettings: _*)

(sourceDirectories in (Compile, JsKeys.js)) <<=
  (sourceDirectory in Compile) {
    srcDir =>
      Seq(
        srcDir / "js",
        srcDir / "coffee"
      )
  }

(Keys.compile in Compile) <<= Keys.compile in Compile dependsOn (JsKeys.js in Compile)

(resourceManaged in (Compile, JsKeys.js)) <<= (sourceDirectory in Compile)(_ / "resources" / "public" / "js")

seq(lessSettings: _*)

(sourceDirectories in (Compile, LessKeys.less)) <<=
  (sourceDirectory in Compile) {
    srcDir =>
      Seq(
        srcDir / "css",
        srcDir / "less"
      )
  }

(Keys.compile in Compile) <<= Keys.compile in Compile dependsOn (LessKeys.less in Compile)

(resourceManaged in (Compile, LessKeys.less)) <<= (sourceDirectory in Compile)(_ / "resources" / "public" / "css")

seq(com.github.bigtoast.sbtthrift.ThriftPlugin.thriftSettings: _*)

com.github.retronym.SbtOneJar.oneJarSettings

org.scalastyle.sbt.ScalastylePlugin.Settings

assemblySettings

scalariformSettings

name := "wpt"

version := "0.1.0"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "OSChina Maven Repo" at "http://maven.oschina.net/content/groups/public/",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases",
  "twitter" at "http://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "com.top10" %% "scala-redis-client" % "1.16.0",
  "com.twitter" %% "util-core" % "6.9.0",
  "com.typesafe" % "config" % "1.0.2",
  "net.debasishg" %% "redisclient" % "2.11",
  "org.apache.thrift" % "libthrift" % "0.9.1",
  "org.mongodb" %% "casbah" % "2.6.4",
  "org.webjars" % "bootstrap" % "3.0.3",
  "com.gensler" %% "scalavro" % "0.6.0",
  "com.twitter" % "finatra" % "1.4.1"
)
