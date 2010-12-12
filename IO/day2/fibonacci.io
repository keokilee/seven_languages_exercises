# Fibonacci implementation using a loop.
fib := method(num,
  
  # Check if it is one or two
  if(num == 1 or num == 2,
    return 1
  );
  
  # Save the n-1 and n-2 terms.
  term1 := 1
  term2 := 1
  temp := 0
  i := 2
  
  while(i < num, 
    temp = term2
    term2 = term1
    term1 = term2 + temp
    i = i + 1
  )
  
  return term1
)

writeln("Fibonacci Sequence (Loop)")
for(i, 1, 10, write(fib(i)); write(" ")); write("\n")

# Fibonacci implementation using recursion
fib := method(num,
  # Base case: return 1 if num is 1 or 2
  if(num == 1 or num == 2,
    return 1
  );
  
  # Return the sum of the previous two.
  return fib(num - 1) + fib(num - 2)
)

writeln("Fibonacci Sequence (Recursive)")
for(i, 1, 10, write(fib(i)); write(" ")); write("\n")