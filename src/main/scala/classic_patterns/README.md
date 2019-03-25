# Classic Gang of Four Patterns

# Creational patterns
Their purpose is to deal with object creation.

## Factory Method
Encapsulate an actual class instantiation.
Provides an interface to create an object, and then the subclasses of the factory decide which concrete class to instantiate.

## Abstract Factory
Encapsulate an actual class instantiation.
Relies on object composition in contrast to inheritance, which is used by the factory method. 
Defines a separate object, which provides an interface to create instances of the classes.

## Builder
Helps to create instances of classes using class methods rather than the class constructors.

## Prototype
Involves creating objects by cloning them from existing ones. 

## Singleton
Ensures that a class has only one object instance in the entire application.
- - -
# Structural patters
Their purpose is composing objects and classes in an application.

## Adapter
Provides a way for incompatible interfaces to work together without modifying their source code.

## Decorator
Provides a way to add functionality to objects without extending them and without affecting the behavior of other objects from the same class.

## Bridge
Provides a way to decouple an abstraction from its implementation so that the two can vary independently.

## Composite
Used to describe groups of objects that should be treated the same way as a single one.

## Façade
Used to provide a simple API for a set of complex classes.

## Flyweight
Used to minimize the memory usage with the help of an object that shares as much data as possible with other similar objects.

## Proxy
Provides a way to access and add functionality to another object by replicating an existing object interface.
- - - 
# Behavioral patters
Their purpose is to identify and implement common communication patterns between objects in an application.

## Mediator
Defines a specific object `mediator` that enables other objects to communicate only via `mediator` but not directly to each other.
Pattern reduces dependency between the components.

## Observer
The purpose is to have an object `observable`(or subject) that automatically notifies all of its `observers` of any state change by calling one of their methods. 

## Chain of Responsibility
Provides a way (hierarchy) to decouple the sender of a request from its receiver by giving multiple objects the chance to handle the request.

## Template
Defines a common skeleton for an algorithm or a family of algorithms.

## State
The purpose of the state design pattern is to allow us to choose a different behavior of an object based on the object's internal state.
Similar to `Strategy` but is about *what* action is performed. Depending on the state, an object could be doing different things.

## Strategy
The strategy design pattern enables us to define a family of algorithms and select a specific one at runtime.
Similar to `State` but is about *how* an action is performed. It is usually an algorithm that produces the same results as other algorithms.

## Visitor
The visitor design pattern helps us add new operations to existing object structures without modifying them.

## Command
Encapsulates an information on how to pass information to other objects about how to perform a certain action.

## Iterator
Provides a way to access the elements of a collection (aggreage object) in a sequential manner without exposing the underlying representation.

## Memento
Provides an ability to restore object state to a previous state.
