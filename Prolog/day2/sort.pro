% Quicksort implementation.

quicksort([], []).
quicksort([Value], [Value]).

quicksort([Head|Tail], List) :-
  % Use the head as the pivot.
  pivot(Head, Tail, Left, Right),
  quicksort(Left, LeftSorted),
  quicksort(Right, RightSorted),
  append(LeftSorted, [Head|RightSorted], List).
  
% Base case of pivot.  If there are no items, then both are empty.
pivot(_, [], [], []).

% Places Head in Right if it is less than or equal to the pivot value.
pivot(Value, [Head|Tail], [Head|Left], Right) :-
  Head =< Value,
  pivot(Value, Tail, Left, Right).
  
% Places Head in Right if it is greater than the pivot value.
pivot(Value, [Head|Tail], Left, [Head|Right]) :-
  Head > Value,
  pivot(Value, Tail, Left, Right).