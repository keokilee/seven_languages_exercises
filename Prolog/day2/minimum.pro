minimum([Value], Value).

minimum([Head|Tail], Value) :- minimum(Tail, Temp), Value is min(Head, Temp).