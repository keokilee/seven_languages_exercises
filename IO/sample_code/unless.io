unless := method(
    (call sender doMessage(call message argAt(0))) ifFalse( 
    call sender doMessage(call message argAt(1))) ifTrue( 
    call sender doMessage(call message argAt(2)))
)

unless(1 == 2, write("One is not two\n"), write("one is two\n"))

Westley := Object clone
Westley trueLove := true
westley := Westley clone

princessButtercup := Object clone
westley unless(princessButtercup trueLove, ("it is false" println), ("it is true" println))