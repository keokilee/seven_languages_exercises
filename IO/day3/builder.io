Builder := Object clone

Builder depth := 0

Builder indent := method(level,
  indent_string := "" asMutable
  for(i, 0, level * 2, 
    # 32 is the ascii code for a space.
    indent_string append(32)
  )
  
  return indent_string
)

Builder forward := method(
  # Check if the first argument is a list of parameters.
  attr_check := self doMessage(call message arguments at(0))
  if(attr_check type == "Map",
    write(self indent(self depth), "<", call message name)
    attr_check foreach(key, value,
      write(" ", key, "=\"", value, "\"")
    )
    writeln(">"),
    
    # Else, we simply write the line
    writeln(self indent(self depth), "<", call message name, ">")
  )
  
  self depth = self depth + 1
  call message arguments foreach(arg,
    content := self doMessage(arg); 
    if(content type == "Sequence", 
      writeln(self indent(self depth), content)
    )
    # Note that we ignore Maps that come from the parameter list.
  )
  self depth = self depth - 1
  writeln(self indent(self depth), "</", call message name, ">")
)

curlyBrackets := method(
  r := Map clone 
  call message arguments foreach(arg,
    test := r doMessage(arg)
    writeln(test)
  )
  r
) 

OperatorTable addAssignOperator(":", "atPutAttr")

curlyBrackets := method(
  r := Map clone
  call message arguments foreach(arg,
    # Modification force IO to handle string as a whole
    # Just doing the message makes IO think it is between sequences
    r doString(arg asString)
  )
  
  r
)
Map atPutAttr := method(
  self atPut(
    call evalArgAt(0) asMutable removePrefix("\"") removeSuffix("\""),
    call evalArgAt(1)
  )
)

Builder ul({"type": "list"},
  li("Io"),
  li("Lua" ), 
  li("JavaScript")
)