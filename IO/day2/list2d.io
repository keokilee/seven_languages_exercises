List2d := Object clone

List2d dim := method(x, y,
  self the_list := List clone
  
  for(i, 0, y - 1, 
    new_list := List clone
    for(j, 0, x - 1,
      new_list append(0)
    )
    
    self the_list append(new_list)
  )
)

List2d set := method(x, y, value,
  self the_list at(y) atPut(x, value)
)

List2d get := method(x, y,
  return self the_list at(y) at(x)
)

List2d transpose := method(
  new_list := self clone
  new_list the_list = new_list the_list reverse()
  
  for(i, 0, new_list the_list size - 1, 
    new_list the_list atPut(i, new_list the_list at(i) reverse())
  )
  
  return new_list
)

test := List2d clone
test dim(2, 2)
test set(0, 1, 2)
writeln(test get(0, 1))
test = test transpose()
writeln(test get(1, 0))