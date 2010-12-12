test_list := list(list(1, 2), list(3, 4))

summation2d := method(the_list,
  sum := 0
  
  # For loops in IO are inclusive
  for(i, 0, the_list size - 1, 
    sum = sum + (the_list at(i) sum)
  )
  
  return sum
)

writeln(summation2d(test_list))