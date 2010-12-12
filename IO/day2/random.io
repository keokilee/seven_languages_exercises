# Import the Random library.  Tricky one to figure out.
Importer AddonImporter import("Random")

# Get a random number.  Random value(99) generates a value between 0 and 99 inclusive.
value := (Random value(99) + 1) floor()

previous := -1
guess := 0

# Shorthand for "repeat 10 times"
10 repeat(
  guess = File standardInput readLine("Guess the number between 1 and 100: ") asNumber()
  # Check if the user is correct.
  if(value == guess,
    writeln("Correct!")
    return
  )
  
  # Check if we need to initialize previous.
  if(previous < 0, previous = guess,
    # Else, we need to check if the person is getting warmer.
    prev_diff := (previous - value) abs
    diff := (guess - value) abs
    
    if(diff < prev_diff, writeln("Warmer"), writeln("Colder"))
    previous = guess
  )
  
)
write("The answer was ")
writeln(value)