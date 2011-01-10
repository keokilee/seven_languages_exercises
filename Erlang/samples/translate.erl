-module(translate).
-export([loop/0]).

loop() ->
  receive
    "casa" ->
      io:format("house~n"),
      loop();
      
    "blanca" ->
      io:format("white~n"),
      loop();
      
    _ ->
      io:format("No lo comprendo~n"),
      loop()
end.