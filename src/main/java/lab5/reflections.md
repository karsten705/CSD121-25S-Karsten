2.5 We were able to return HumanPlayer's object because
it's a sub-class of the Player class. Since we can not 
instantiate an object of an abstract class, we need to
return an object of HumanPlayer class (sub-type polymorphism).


2.9 
i. The error was resolved because we specified the Player class
as an abstract class, so it can contain an abstract method.
Since we are providing the implementation of pickNextMove() in the sub-class
"HumanPlayer" class, it does not throw an error.

ii. The type of "whoseTurn" variable is Player class.

5
The pickNextMove() is implemented for each of the sub-class of type Player. Since,
Player class is an abstract class, the sub-classes that extends it has their
own definition. So, the Randy and Omola does not need to modify the doNextTurn()
at all, since they just use the public interface of their own class. We are just invoking the respective methods of either Randy or Omola according
to the player selected at run-time.

Therefore, it does not matter whose turn it is or what type of player is calling 
since they are sub-types of the Parent - Player class (abstract).

