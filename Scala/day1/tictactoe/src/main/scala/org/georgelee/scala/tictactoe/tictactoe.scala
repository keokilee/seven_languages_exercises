package org.georgelee.scala.tictactoe

object Player extends Enumeration {
  //Only two players.
  type Player = Value
  val Player1, Player2 = Value
}

class InvalidBoardException(e: String) extends Exception(e)

class InvalidSlotException(e: String) extends Exception(e)

class TicTacToe(val board: Array[String]) {
  // Validate our board.
  validate()
  
  def validate() {
    if(this.board.size != 9) {
      throw new InvalidBoardException("Invalid board length")
    }
    
    //Check for invalid characters
    val invalidChars = this.board.filter(value => value != "x" && value != "o" && value.size > 0)
    if(invalidChars.size > 0) {
      //List concatenation found at http://blog.aggregat4.net/a-stringjoinlist-for-scala-using-reduceleft
      throw new InvalidBoardException("Found invalid characters: " + invalidChars.reduceLeft(_ + ", " + _))
    }
  }
  
  
  def getBoard = this.board
  
  def isSlotAvailable(location: Int): Boolean = this.board(location).size == 0
  
  def insert(player: Player.Value, location: Int) {
    if(location < 0 || location > 8) {
      throw new InvalidSlotException("Invalid location.")
    }
    
    if(this.board(location).size > 0) {
      throw new InvalidSlotException("This slot is already taken")
    }
    
    if(player == Player.Player1) {
      this.board(location) = "x"
    }
    
    else {
      this.board(location) = "o"
    }
  }
  
  def hasWinner(): Boolean = {
    //Check each row
    for(i <- 0 until (8, 3)) {
      if(!this.isSlotAvailable(i) && this.board(i) == this.board(i+1) && 
                                  this.board(i+1) == this.board(i+2)) {
        return true
      }
    }
    
    //Check each column
    for(i<-0 until 3) {
      if(!this.isSlotAvailable(i) && this.board(i) == this.board(i+3) && 
                                  this.board(i+3) == this.board(i+6)) {
        return true
      }
    }
    
    //Check the diagonals.
    if(!this.isSlotAvailable(4) && this.board(4) == this.board(2) && 
                                this.board(4) == this.board(6)) {
      return true
    }
    else if(!this.isSlotAvailable(4) && this.board(4) == this.board(0) && 
                                this.board(4) == this.board(8)) {
      return true
    }
    
    return false
  }
  
  def movesAvailable(): Number = {
    var count = 0
    for(i <- 0 until this.board.size) {
      if(this.isSlotAvailable(i)) {
        count += 1
      }
    }
    
    return count
  }
  
  def clearBoard() = {
    for(i <- 0 until this.board.size) {
      this.board(i) = ""
    }
  }
  
  
  def printBoard() = {
    var buffer = new StringBuffer()
    //Go through and append each element.
    for(i <-0 until this.board.size) {
      if(this.board(i).size == 0) {
        buffer.append(" ")
      }
      else {
        buffer.append(this.board(i))
      }
      
      if(i % 3 == 2) {
        buffer.append("\n")
      }
      else {
        buffer.append("|")
      }
      
      if(i % 3 == 2 && i != this.board.size - 1) {
        buffer.append("-----\n")
      }
    }
    
    println(buffer.toString)
  }
}

object TicTacToeMain {
  def main(args: Array[String]) {
    println("Welcome to tic tac toe!")
    var currentPlayer = Player.Player1
    val game = new TicTacToe(Array("", "", "", "", "", "", "", "", ""))
    game.printBoard
    
    while(true) {
      //User prompt
      if(currentPlayer == Player.Player1) {
        print("Player 1, please choose a slot (1-9) ")
      }
      else {
        print("Player 2, please choose a slot (1-9) ")
      }
      
      //Loop to catch invalid input.
      var valid = false
      while(!valid) {
        try {
          val input = readInt
          println(input)
          game.insert(currentPlayer, input - 1)
          print("\n")
          valid = true
        }
        catch {
          case ise: InvalidSlotException => print("\nPlease try again. ")
          case nfe: NumberFormatException => print("\nPlease try again. ")
        }
      }
      
      game.printBoard
      
      //Check for winners and swap players.
      if(currentPlayer == Player.Player1) {
        if(game.hasWinner) {
          println("Player 1 has won the game")
          return
        }
        currentPlayer = Player.Player2
      }
      else {
        if(game.hasWinner) {
          println("Player 2 has won the game")
          return
        }
        
        currentPlayer = Player.Player1
      }
      
      if(game.movesAvailable == 0) {
        println("This game is a tie.")
        return
      }
    }
  }
}