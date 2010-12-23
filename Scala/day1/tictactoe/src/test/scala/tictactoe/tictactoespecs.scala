package tictactoe

import org.junit.runner.RunWith
import org.specs._
import org.specs.matcher._
import org.specs.runner.{ JUnitSuiteRunner, JUnit }
import org.georgelee.scala.tictactoe.{TicTacToe, InvalidBoardException, InvalidSlotException}
import org.georgelee.scala.tictactoe.Player._
//import org.scalacheck.Gen

/**
 * Sample specification.
 * 
 * This specification can be executed with: scala -cp <your classpath=""> ${package}.SpecsTest
 * Or using maven: mvn test
 *
 * For more information on how to write or run specifications, please visit: http://code.google.com/p/specs.
 *
 */
@RunWith(classOf[JUnitSuiteRunner])
class TicTacToeSpecTest extends Specification with JUnit /*with ScalaCheck*/ {
  var game: TicTacToe = null
  
  "TicTacToe" should {    
    "require a board with a valid length" in {
      new TicTacToe(Array("foo")) must throwA[InvalidBoardException]
    }
    
    "require a board with valid characters" in {
      val board = Array("f", "f", "f", "f", "f", "f", "f", "f", "f")
      new TicTacToe(board) must throwA[InvalidBoardException]
    }
    
    "accept a valid board" in {
      val board = Array("", "x", "o", "", "x", "o", "", "x", "o")
      new TicTacToe(board) must notBeNull
    }
  }

  "A valid board" should {
    doBefore {
      this.game = new TicTacToe(Array("", "", "", "", "", "", "", "", ""))
    }
    
    "allow player 1 to place an x into a valid slot" in {
      this.game.insert(Player1, 4)
      this.game.getBoard(4) must be_==("x")
    }
    
    "not allow player 1 to place an x into a invalid slot" in {
      this.game.insert(Player1, 10) must throwA[InvalidSlotException]
    }
    
    "allow player 2 to place an o into a valid slot" in {
      this.game.insert(Player2, 4)
      this.game.getBoard(4) must be_==("o")
    }
    
    "not allow player 1 to place into the same slot twice" in {
      this.game.insert(Player1, 4)
      this.game.insert(Player1, 4) must throwA[InvalidSlotException]
    }
    
    "not allow player 2 to place into the same slot as player 1" in {
      this.game.insert(Player1, 4)
      this.game.insert(Player2, 4) must throwA[InvalidSlotException]
    }
  }
  
  "hasWinner" should {
    "not have a winner for an empty board" in {
      val game = new TicTacToe(Array("", "", "", "", "", "", "", "", ""))
      game.hasWinner() must beFalse
    }
    
    "have a winner in the first row" in {
      val board = Array("", "", "", "x", "x", "x", "", "", "")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
    
    "have a winner in the second row" in {
      val board = Array("", "", "", "o", "o", "o", "", "", "")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
    
    "have a winner in the third row" in {
      val board = Array("", "", "", "", "", "", "x", "x", "x")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
    
    "have a winner in the first column" in {
      val board = Array("o", "", "", "o", "", "", "o", "", "")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
    
    "have a winner in the second column" in {
      val board = Array("", "x", "", "", "x", "", "", "x", "")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
    
    "have a winner in the third column" in {
      val board = Array("", "", "o", "", "", "o", "", "", "o")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
    
    "have a winner in diagonal top left to bottom right" in {
      val board = Array("x", "", "", "", "x", "", "", "", "x")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
    
    "have a winner in diagonal top right to bottom left" in {
      val board = Array("", "", "o", "", "o", "", "o", "", "")
      val game = new TicTacToe(board) 
      game.hasWinner() must beTrue
    }
  }
  
  "movesAvailable" should {
    "be 9 in a empty board" in {
      val board = Array("", "", "", "", "", "", "", "", "")
      val game = new TicTacToe(board) 
      game.movesAvailable() must be_==(9)
    }
    
    "be 1 after one player goes" in {
      val board = Array("", "", "", "", "x", "", "", "", "")
      val game = new TicTacToe(board) 
      game.movesAvailable() must be_==(8)
    }
    
    "be 0 in a full board" in {
      val board = Array("x", "o", "x", "o", "x", "o", "x", "o", "x")
      val game = new TicTacToe(board) 
      game.movesAvailable() must be_==(0)
    }
  }
  
  "clearBoard" should {
    "clear a full board" in {
      val board = Array("x", "o", "x", "o", "x", "o", "x", "o", "x")
      val game = new TicTacToe(board)
      game.clearBoard()
      game.movesAvailable() must be_==(9)
    }
  }
}

object TicTacToeSpecMain {
  def main(args: Array[String]) {
    new TicTacToeSpecTest().main(args)
  }
}
