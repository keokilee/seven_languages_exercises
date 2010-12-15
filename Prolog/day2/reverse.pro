/*reverse([], []).
reverse([Head|Tail], List) :- reverse(Tail, Temp), append(Temp, [Head], List).*/

% better implementation from http://en.wikibooks.org/wiki/Prolog/Lists
revappend([], List, List).
revappend([Head|Tail], List, List2) :- revappend(Tail, [Head|List], List2).
reverse(List, List2) :- revappend(List, [], List2).