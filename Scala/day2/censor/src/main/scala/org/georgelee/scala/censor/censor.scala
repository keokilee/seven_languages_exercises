package org.georgelee.scala.censor

import scala.collection.mutable.HashMap

trait Censor {
  val censorDict = new HashMap[String, String]
  
  def censor(input: String): String = {
    val wordList = input.split(" ")
    val censoredList = wordList.map(word => this.censorToken(word))
    return censoredList.reduceLeft(_ + " " + _)
  }
  
  def loadFile = {
    //Solution from http://stackoverflow.com/questions/1284423/read-entire-file-in-scala
    val lines = io.Source.fromFile("resources/dictionary.txt", "utf-8").getLines
    lines.foreach(line => parseLine(line))
  }
  
  private def parseLine(line: String) {
    val words = line.split(", ")
    this.censorDict += words(0) -> words(1)
  }
  
  private def censorToken(word: String): String = {
    // Split word from punctuation.
    val punctuationRegex = "[^a-zA-Z0-9]+$".r
    val punctuation = ("" /: punctuationRegex.findAllIn(word)) {(punct, c) => punct + c}
    val strippedWord = punctuationRegex.split(word)(0)
    
    
    // Check if the dictionary contains the word in question
    if(censorDict.keys.exists(key => key.equals(strippedWord.toLowerCase))) {
      //Need to check for capitalization.  Can't maintain capitalization in the middle of the word.
      if(strippedWord(0).isUpper) {
        return censorDict(strippedWord.toLowerCase).capitalize + punctuation
      }
      return censorDict(strippedWord.toLowerCase) + punctuation
    }
    
    // If not, just return the word
    return word
  }
}