# START:range
OperatorTable addAssignOperator(":", "atPutNumber")
# END:range
# START:curlyBrackets
curlyBrackets := method(
  r := Map clone
  call message arguments foreach(arg,
         r doString(arg asString)
         )
  r
)

# END:curlyBrackets
# START:atPutNumber
Map atPutNumber := method(
  self atPut(
       call evalArgAt(0) asMutable removePrefix("\"") removeSuffix("\""),
       call evalArgAt(1))
)

# END:atPutNumber
# START:use
# s := File with("phonebook.txt") openForReading contents
# writeln(s)
# phoneNumbers := doString(s)
phoneNumbers := {"Bob Smith": "5195551212", "Mary Walsh": "4162223434"}
phoneNumbers keys   println
phoneNumbers values println
# END:use