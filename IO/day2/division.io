# Save original division
Number setSlot("coreDivision", Number getSlot("/"))

Number setSlot("/",
  method(denominator,
    if(denominator == 0,
      return 0
    )
    
    return self coreDivision(denominator)
  )
)

writeln("Overrided division operator")
write("1/0 = ")
writeln(1/0)

