# [1, 2, 3, 4]

squareBrackets := method(
  the_list := List clone
  call message arguments foreach(arg,
    the_list append(the_list doMessage(arg))
  )
  
  return the_list
)

test_list := [1, 2, 3, "foo", [4, 5]]

writeln(test_list)