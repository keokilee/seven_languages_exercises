package samples

import org.junit.runner.RunWith
import org.specs._
import org.specs.matcher._
import org.specs.runner.{ JUnitSuiteRunner, JUnit }
import org.georgelee.scala.censor.Censor
//import org.scalacheck.Gen

/**
 * Test class for specs.
 *
 */
class Person

class Sailor extends Person with Censor {
  this.loadFile
  
  def speak(phrase: String): String = {
    return this.censor(phrase)
  }
}

@RunWith(classOf[JUnitSuiteRunner])
class CensorSpecTest extends Specification with JUnit /*with ScalaCheck*/ {
  val sailor = new Sailor()
  
  "Censor" should {
    "repeat phrases with no censored words" in {
      val phrase = "Ahoy, mate!"
      this.sailor.speak(phrase) must be_==(phrase)
    }
    
    "censor darn with beans" in {
      val phrase = "Gosh darn it!"
      this.sailor.speak(phrase) must be_==("Gosh beans it!")
    }
    
    "censor shoot with pucky" in {
      val phrase = "Aw shoot man!"
      this.sailor.speak(phrase) must be_==("Aw pucky man!")
    }
    
    "maintain capitalization for the first letter" in {
      val phrase = "Darn it!"
      this.sailor.speak(phrase) must be_==("Beans it!")
    }
    
    "maintain punctuation at the end" in {
      val phrase = "Shoot!"
      this.sailor.speak(phrase) must be_==("Pucky!")
    }
  }
  
}

object CensorSpecMain {
  def main(args: Array[String]) {
    new CensorSpecTest().main(args)
  }
}
