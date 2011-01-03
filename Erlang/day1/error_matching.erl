-module(error_matching).
-export([error_handler/1]).

error_handler(success) -> io:fwrite("success~n", []);
error_handler({error, Message}) -> io:fwrite("error: ~s~n", [Message]).