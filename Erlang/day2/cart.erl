-module(cart).
-export([total/1]).

% Total takes one parameter, the cart.
total(Cart) -> [{Product, Price * Quantity} || {Product, Quantity, Price} <- Cart].