-module(count).
-export([count/1]).

count(1) -> io:fwrite("~B~n", [1]);
count(N) -> count(N-1), io:fwrite("~B~n", [N]).