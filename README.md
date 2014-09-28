Introduction
------------
So the company I work for asked me to come up with a suitable problem that can be handed to
candidates prior to their coming in for an on-site interview. Against my urging, there was a 
two hour time limit placed on implementing the solution. At the time, I decided to do the
exercise myself, as a feasibility check on the problem restrictions and scope, and that effort
is the contents of this repository. 

Note that since then, managment took my advice and changed it to an overnight coding exercise.


Original Problem Statement
--------------------------

Design the domain model for an object oriented vending machine that can handle different 
products, can accept payments, and can vend product. Do not model an interactive menu loop!
Given that this is a limited interview question, you can limit the level of detail that you 
model at.

Commentary
----------

Design of a vending machine is a common interview-related problem. The point of this is to 
demonstrate some minimal knowledge of abstraction and design. This is the chance for the candidate
to showcase their skills. In my solution, there are some design, best practices, and software quality points that are captured as comments so you'll have to scan the code. 

Compare this solution to some found on the web like those below that tend to focus
on mastery of the language, yet exhibit inferior design. My solution is not the only way to
design for this problem. For example, if the problem is more at a hardware controller level where
there are more detailed inputs and responses required, say modeling of individual button presses, 
you might use a state machine to capture the internal behavior.


http://code.google.com/a/eclipselabs.org/p/vending-machine/source/browse/VendingMachine/src/VendingMachine/VendingMachine.java?r=3

http://www.daniweb.com/software-development/java/threads/179994/java-vending-machine-program

http://www.coderanch.com/t/621654/java/java/Vending-Machine-Assignment

