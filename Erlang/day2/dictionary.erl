-module(dictionary).
-export([value/2]).

% Value takes two parameters: the list and the key.
value(List, Keyword) -> [Value || {Key, Value} <- List, Keyword == Key], Value.