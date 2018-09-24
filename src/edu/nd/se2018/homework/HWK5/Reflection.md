# Homework 5 Reflection
Anthony Luc (aluc)
September 25, 2018

# Responses

### Reflect on any design changes you made and write a paragraph or two explaining how you approached the design process, and ultimately what you liked and didn’t like about your decisions. 

Overall, I wanted to try to reuse as much code as possible and to maintain the core design thematic of the code. That being said, I made ---.

### In preparation for our future architectural focus, imagine that Mayor Buttigieg wants to build a simulation of traffic in South Bend and he asks if he can use your design – discuss whether your design (as-is) would scale up or not. List specific aspects that you think wouldn’t scale well.

In order to add logic to differentiate between a westbound train versus an eastbound train, I simply used if-statements. This is not very clean because if the desin were to begin incorporating northbound or southbound trains, then I'd have to always have four if-statements and handle logic like that. Therefore, I'd want to create some sort of interface or way to separate out this changing aspect.

TODO List:
* One
* Two
