-module(doctor).
-export([loop/0, start/0]).

loop() ->
  process_flag(trap_exit, true),
  receive
    new ->
      io:format("Creating and monitoring process.~n"),
      register(revolver, spawn_link(fun roulette:loop/0)),
      loop();
      
    die -> 
      io:format("Shot the doctor.~n"), 
      exit({doctor, die, at, erlang:time()});
    
    {'EXIT', From, Reason} ->
      io:format("The shooter ~p died with reason ~p.", [From, Reason]),
      io:format(" Restarting.~n"),
      self() ! new,
      loop()    
  end.
  
start() ->
  process_flag(trap_exit, true),
  receive
    new ->
      io:format("Creating and monitoring self.~n"),
      register(doctor, spawn_link(fun doctor:loop/0)),
      start();
    
    {'EXIT', From, Reason} ->
      io:format("The doctor ~p died with reason ~p.", [From, Reason]),
      io:format(" Restarting.~n"),
      self() ! new,
      start()    
  end.