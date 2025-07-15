2.5 We were able to return HumanPlayer's object because
it's a sub-class of Player class. Since, we cannot 
instantiate an object of an abstract class, we need to
return an object of HumanPlayer class (sub-type polymorphism).


2.9 
i. The error was resolved because we specified the Player class
as an abstract class and thus, it can contain an abstract method.
Since we are providing the implementation of pickNextMove() in the sub-class
HumanPlayer class, so it does not throw an error.

ii. The type of "whoseTurn" variable is Player class.
