Final Reality
=============

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com), as a part of the work assigned by CC3002 Design
and Programming Methodologies college course.
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Prerequisites

Gradle Build Tool.
Java
JUnit

Recommended: 
- Java 11 or superior.
- JUnit 5.0 or superior.
- IntelliJ IDE.

## Installing

Cloning the repository onto your computer, then opening the project using an IDE that
supports the recommended Java version.

## Running the tests

Executing the gradle build will run the testing process.
On a side note, sometimes the gradle build doesn't execute any of the tests, so executing it only once 
and then running the build again should solve this. This might be related to compatibility issues 
with my personal machine.

# General Summary

The project is built under the Model-View-Controller software arquitecture pattern, also following 
SOLID principles, along with others programming paradigms, containing an exhaustive amount of tests
to ensure a solid, functional result. 

## Model Summary
### Partial homework #1

The project got started by programming the model first. It consists on the characters and the weapons 
they may equip. Those entities are coded in such hierarchy that allows future extensions without
altering, in a major way, already written code. 

Some of the base code was altered by the student's criteria so it would fit with the SOLID principles 
and other paradigms such as Liskov's Principle.
By example, the ICharacter interface got the equip(IWeapon weapon) and getEquippedWeapon() methods
deleted and transferred to the IPlayerCharacter interface, since only playable characters may
equip weapons, and others such as Enemy entities can't do it, according to the requested design 
assigned by the course. All the entities got ordered following an according hierarchy:
[Insert UML image]

A short summary of the rest of the work and added features:
- Additional stats such as HP, atk, def, etc., were added to the respective type characters.
- An interface IWeapon, along with an abstract class AbstractWeapon and subsequents tangible
weapons (Axe, Sword, etc.).
- Additional interfaces for the character entities to distinguish them from others, since Enemies
are different from playable characters, and inside those, common characters are different from mages ones.
- An asbtract entity for mages and tangible classes for Black and White Mages, since there aren't "untyped"
mages.

# Deployment

Add additional notes about how to deploy this on a live system

# Versioning

For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

# Authors

* **Ignacio Slater** - *Initial work* - [islaterm](https://github.com/islaterm)
* **Rodrigo G. Oportot** - *Student work* - [dodii](https://github.com/dodii)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

# License

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

# Acknowledgments

* Slater
* Bergel
* Nancy
* Ephyy, Woiic, Gresh and F0t0n, for helping me.
