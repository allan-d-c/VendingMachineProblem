Problem
-------

Design an object oriented vending machine that can handle different products, can 
accept payments, and can vend product. Given that this is a limited interview 
question, you can limit the level of detail that you model at.

Scope
-----

Design of a vending machine is a common interview-related problem. So here is my take
at it. The point of this is to demonstrate some minimal knowledge of abstraction and
design. There are some design points that are captured as comments in the code. 

Compare this solution to some found on the web like those below that tend to focus
on master of the language, yet exhibit inferior design. There are many ways to
design for this, depending on how rigorous the requirements are. 

For example, if there are more inputs and responses required, say modeling of 
individual button presses, you might use a state machine to capture the internal 
behavior.


http://code.google.com/a/eclipselabs.org/p/vending-machine/source/browse/VendingMachine/src/VendingMachine/VendingMachine.java?r=3

http://www.daniweb.com/software-development/java/threads/179994/java-vending-machine-program

http://www.coderanch.com/t/621654/java/java/Vending-Machine-Assignment

