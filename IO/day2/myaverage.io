List myAverage := method(throw_exception,
  # Initial values
  sum := 0
  numbers := 0
  for(i, 0, self size - 1,
    value := self at(i)
    if(value isKindOf(Number),
      sum = sum + value
      numbers = numbers + 1
    )
    
    if (value != Number and throw_exception,
      Exception raise("Invalid type in list.")
    )
  )
  
  if(numbers == 0) then(return 0) else(return sum / numbers)
)

test_list := list(1, 2, "foo")

writeln(test_list myAverage(false))
writeln(test_list myAverage(true))