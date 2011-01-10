-module(translate_service).
-export([loop/0, translate/2]).

loop() ->
  receive
    {From, "casa"} ->
      From ! "house",
      loop();
      
    {From, "blanca"} ->
      From ! "white",
      loop();
      
    {From, _} ->
      From ! "No lo comprendo",
      loop()
end.
    
translate(To, Word) ->
  To ! {self(), Word},
  receive
    Translation -> Translation
  end.
  