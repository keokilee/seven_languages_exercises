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
  
  def getUrl = this.url
  
  def findSrcLinks: HashSet[String] = {
    if(this.contents == null) {
      println("Loading contents of " + this.url)
      this.contents = this.dumpUrl
    }
    
    val srcSet = new HashSet[String]
    srcMatcher.findAllIn(contents).foreach(url => srcSet.add(processUrl(url)))
    return srcSet
  }
  
  def findHrefLinks: List[String] = {
    if(this.contents == null) {
      println("Loading contents of " + this.url)
      this.contents = this.dumpUrl
    }
    
    val hrefSet = new HashSet[String]
    //Filter out within page links, since they don't go anywhere.
    val links = hrefMatcher.findAllIn(contents).filter(url => !url.startsWith("href=\"#") && !url.startsWith("href=\"javascript"))
    links.foreach(url => hrefSet.add(processUrl(url)))
    return hrefSet.toList
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

object WebCrawler {               
  def crawlConcurrently(urls: List[String], depth: Int) = {
    val caller = self

    for(url <- urls) {
      val parser = new HtmlParser(url)
      actor { caller ! (url, depth, parser.findHrefLinks) }
    }

    for(i <- 1 to urls.size) {
      receive {
        case (url, depth, links) => processResults(url.asInstanceOf[String], depth.asInstanceOf[Int], 
                                    links.asInstanceOf[List[String]])        
      }
    }
  }
  
  def processResults(url: String, depth: Int, links: List[String]) {
    println("Number of links in " + url + ": " + links.size)
    if(depth > 0) {
      crawlConcurrently(links, depth - 1)
    }
  }
}

def timeMethod(method: () => Unit) = {
 val start = System.nanoTime
 method()
 val end = System.nanoTime
 println("Method took " + (end - start)/1000000000.0 + " seconds.")
}

val urls = List("http://www.cnn.com/")
WebCrawler.crawlConcurrently(urls, 1)

