# Homework 5 Reflection
Anthony Luc (aluc)
September 25, 2018

# Responses

### Reflect on any design changes you made and write a paragraph or two explaining how you approached the design process, and ultimately what you liked and didn’t like about your decisions. 

Overall, I wanted to try to reuse as much code as possible and to maintain the core design thematic of the code. That being said, I made my first change to the CrossingGate class. Here, I added a HashSet which kept track of the number of nearby trains. If a train is within range, it would be added to this set. When no trains are in range, the logic would compute that there are 0 trains within range, so the gate would open. I like this design because if I end up added X number of
trains to the setup, it would properly handle those trains without needing to change my crossing gate logic.

My second change for handling cars changing roads was not as clean, and it is still a bit buggy. I added a new function to the simulation which handled the "adding" and "removing" of the cars from the "linked lists". I tried to ensure that the linkage of observers were all correctly realigned, but it seems that it's not perfect. I think I mostly got hung up on the merging from the middle road onto the left road. I tried to extend the Car class to include an isTryingToMerge
boolean to better facilitate this, but I must still be missing something. I think two things I could have done are: (1) create a "Junction" class that represents these road junctions and (2) implement the State design pattern for cars. If I had implemeted (1), I could use it like the CrossingGate class to observe all cars in vicinity to determine if the road was clear for merging. If I had implemented (2), I could better handle the state of the car to be either waiting,
following, or merging.

### In preparation for our future architectural focus, imagine that Mayor Buttigieg wants to build a simulation of traffic in South Bend and he asks if he can use your design – discuss whether your design (as-is) would scale up or not. List specific aspects that you think wouldn’t scale well.

In order to add logic to differentiate between a westbound train versus an eastbound train, I simply used if-statements. This is not very clean because if the design were to begin incorporating northbound or southbound trains (or even diagonal trains), then I'd have to add more if-statements and add additional logic. Therefore, I'd want to create some sort of interface or way to separate out this changing aspect and ensure that train movement will be handled correctly
regardless of their orientation. That being said, the current implementation of the CrossingGate will allow for many more trains to be added and still work (though perhaps the trigger range should be changed to be a function of the number of train tracks).

For the cars, I don't think that my design as-is will scale well. If all cars simply moved southbound, then perhaps it will scale well. But realistically, Mayor Buttigieg would like to have roads going in other directions, and my code is quite 'hard-coded' to handle this one scenario.


