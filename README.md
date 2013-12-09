# Web Project Template

---

1.数据库mongodb，和redis  
2.数据结构avro  
3.前端数据json  
4.模板scalate  

###1. install jdk 7
[java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
###2. install scala 2.10.2
[scala](http://www.scala-lang.org/download/)
###3. install sbt 0.13.0
[sbt](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html)
###4. install scalate 1.6.1
[scalate](http://scalate.fusesource.org/download.html)
```
git clone https://github.com/ixiongdi/unfiltered-scalate.git  
cd unfiltered-scalate  
sbt
publishLocal
```
###5. install thrift 0.9.1
[thrift](http://thrift.apache.org/download/)
###6. sbt run
###7. browser http://localhost:8080/hello
###8. for dev
```
sbt  
~re-start
```
###9. for prod
```
sbt  
oneJar  
exit  
java -jar target/scala-2.10/wpt_2.10-1.0-one-jar.jar
```
###10 codestyle
```
sbt
scalastyleGenerateConfig
scalastyle
```
