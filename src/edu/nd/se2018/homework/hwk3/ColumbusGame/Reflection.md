# Homework 3 Reflection
Anthony Luc (aluc)
September 17, 2018

# Design Decisions

Questions: Reflect on your design. What did you like about
it? If you needed to extend the design to support the new functionality (under stretch)
how would/did you do it? 

**Answer: I really liked how I opted to make a PirateShip class that is a subclass of ship. This allowed me to define the movement method for the pirates in a way that reuses the movement method from the Ship class. Furthermore, I think that this setup would allow me to potentially add new observers which may want to follow the pirate ships (i.e. if this was a game with submarines, I could add a fireMissle method that would add a missle as an observer of the enemy ship). In addition, I chose to wrap the four movement methods (goNorth/South/East/West) with the method "moveShip". By doing that, I was able to easily add the necessary logic to update the oceanGrid from Tile.OCEAN to Tile.SHIP and to edit the location of the imageView. Last, I liked that the way I initially setup my project allowed me to easily add the two stretch implementations. I noticed that I could separate the initialization of the game, the start of the game, and the added UI implementation. Overall, I felt very satisfied with my solution and don't really see any places for significant improvement.**

# UML Diagram
![UML](uml_diagram.png)