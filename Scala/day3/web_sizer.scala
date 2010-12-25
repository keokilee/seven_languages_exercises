import scala.actors._
import Actor._
import scala.io.Source
import scala.xml.XML
import scala.io.Source
import scala.collection.mutable.HashSet

class HtmlParser(val url: String) {
  var contents: String = null
  val srcMatcher = "src=\"(.*?)\"".r
  val hrefMatcher = "href=\"(.*?)\"".r
  
  def findSrcLinks: HashSet[String] = {
    if(this.contents == null) {
      println("Loading contents of " + this.url)
      this.contents = this.dumpUrl
    }
    
    val srcSet = new HashSet[String]
    srcMatcher.findAllIn(contents).foreach(url => srcSet.add(processUrl(url)))
    return srcSet
  }
  
  def findHrefLinks: HashSet[String] = {
    if(this.contents == null) {
      println("Loading contents of " + this.url)
      this.contents = this.dumpUrl
    }
    
    val hrefSet = new HashSet[String]
    //Filter out within page links, since they don't go anywhere.
    val links = hrefMatcher.findAllIn(contents).filter(url => !url.startsWith("href=\"#"))
    links.foreach(url => hrefSet.add(processUrl(url)))
    return hrefSet
  }
  
  private def dumpUrl: String = {
    return Source.fromURL(this.url).mkString
  }
  
  private def processUrl(url: String): String = {
    val returnUrl = url.stripPrefix("src=").stripPrefix("href=\"").stripSuffix("\"")
    if(returnUrl.startsWith("/")) {
      return this.url.stripSuffix("/") + returnUrl
    }
    else if(!returnUrl.startsWith("http")) {
      return this.url.stripSuffix("/") + "/" + returnUrl
    }
    return returnUrl 
  }
}

// START:loader
object PageLoader {
 def getPageSize(url : String) = Source.fromURL(url).mkString.length
 def getNumLinks(url: String) = {
   val parser = new HtmlParser(url)
   parser.findHrefLinks.size
 }
}
// END:loader

val urls = List("http://www.amazon.com/", 
               "http://www.twitter.com/",
               "http://www.google.com/",
               "http://www.cnn.com/" )

// START:time
def timeMethod(method: () => Unit) = {
 val start = System.nanoTime
 method()
 val end = System.nanoTime
 println("Method took " + (end - start)/1000000000.0 + " seconds.")
}
// END:time

// START:sequential
def getPageSizeSequentially() = {
 for(url <- urls) {
   println("Size of " + url + ": " + PageLoader.getPageSize(url))
 }
}

def getNumLinksSequentially() = {
 for(url <- urls) {
   println("Number of links in " + url + ": " + PageLoader.getNumLinks(url))
 }
}
// END:sequential

// START:concurrent
def getPageSizeConcurrently() = {
 val caller = self

 for(url <- urls) {
   actor { caller ! (url, PageLoader.getPageSize(url)) }
 }

 for(i <- 1 to urls.size) {
   receive {
     case (url, size) =>
       println("Size of " + url + ": " + size)            
   }
 }
}

def getNumLinksConcurrently() = {
 val caller = self

 for(url <- urls) {
   actor { caller ! (url, PageLoader.getNumLinks(url)) }
 }

 for(i <- 1 to urls.size) {
   receive {
     case (url, size) =>
       println("Number of links in " + url + ": " + size)            
   }
 }
}
// END:concurrent

// START:script
println("Sequential page size run:")
timeMethod { getPageSizeSequentially }

println("Concurrent page size run:")
timeMethod { getPageSizeConcurrently }

println("Sequential number of links run:")
timeMethod { getNumLinksSequentially }

println("Concurrent number of links run:")
timeMethod { getNumLinksConcurrently }
// END:script