-module(num_words).
-export([num_words/1]).

% Recursive function for counting words.  Not exported by the module.
% First argument is the sentence.  Second argument is true if we are in a word, false otherwise.
num_words_recursive([], false) -> 0;
% 32 corresponds to a space.
num_words_recursive([32|Tail], false) -> num_words_recursive(Tail, false);
% Catches anything that is not a space.
num_words_recursive([_|Tail], false) -> num_words_recursive(Tail, true);

num_words_recursive([], true) -> 1;
num_words_recursive([32|Tail], true) -> 1 + num_words_recursive(Tail, false);
num_words_recursive([_|Tail], true) -> num_words_recursive(Tail, true).

num_words([]) -> 0;
num_words(Words) -> num_words_recursive(Words, false).