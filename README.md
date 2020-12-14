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

The model summary will be explained in a "changelog" kind of way, since the work progress is partitioned
through partial homeworks. Additional and required details will be added for a better insight. Also, all
assumptions made related to the actual programming of the code will be listed too.

### Partial homework #1

The project got started by programming the model. It consists on the characters and the weapons 
they may equip. Those entities are coded in such a hierarchy that allows future extensions without
altering, in a major way, already written code. 

Some of the base code was altered by the student's criteria so it would fit with SOLID principles 
and other paradigms such as Liskov's Principle.
By example, the ICharacter interface got the equip(IWeapon weapon) and getEquippedWeapon() methods
deleted and transferred to the IPlayerCharacter interface, since only playable characters may
equip weapons. Others such as the Enemy entities can't do it, according to the requested design 
assigned by the project. All the entities got ordered following the hierarchy shown on the 
CharactersModel.PNG and WeaponsModel.PNG files.

A short summary of the rest of the work, assumptions made and added features:
- Additional stats such as HP, atk, def, etc., were added to the respective type characters.
- An interface IWeapon, along with an abstract class AbstractWeapon and subsequents concrete
weapons (Axe, Sword, etc.).
- Additional interfaces for the character entities to distinguish them from others, since Enemies
are different from playable characters, and inside those, common characters are different from mage ones.
ICommonCharacter and IMageCharacter both extends from IPlayerCharacter, but they are different since those types
of characters may be sustantially different in the next implementations of the code.
- An asbtract entity for mages and concrete classes for Black and White Mages, since there aren't "untyped"
mages. Also an interface IMageCharacter associated to them.
- As supposed, PlayerCharacter is enough on its own to associate it with the creation of common-class characters 
instances, since there aren't any specializes treats to program for those classes yet (next implementations may
change the class to an abstract one, when double dispatch gets coded for a different version of equip()). When a 
common classcharacter is instanced, it will have the ICommonCharacter interface implemented, nonetheless it will 
remain solely as a placeholder for now. Common class characters will be instanced by their own classes (e.g Knight 
dodi = new Knight(...)) but there shouldn't be a problem (by now) if they are instanced as PlayerCharacter' class, 
like how it's done on the testing classes. By convention, they will always be instanced, ingame, as its most particular 
instance.
- All objects will be created with non-negative or greater than 0 stats, depending on the object.
- The enum types CharacterClass and WeaponType weren't deleted yet, since the model's classes still
use it. When double dispatch gets implemented on other methods, they won't be necessary anymore, because
each class already knows its own "type". It could have been deleted, but it wasn´t just for the sake of 
clarity purposes of this first part of the work.

### Partial homework #2
The testing process has been initiated. The test classes involved are built over a hierarchy, similar
to the project's model.

Before explaining the testing classes, some important assumptions made and other details added to the model:

- Magic Damage stat on the Staff weapon Class.
- Added mana stat to mage characters.
- getDelay() method which returns the delay of a character before acting on its turn, calculated differently
if it's an enemy (uses its own weight) or if it's a playable character (based on its equipped weapon).
- NullWeapon class created, representing an instance of an "unequipped" weapon, with 0 attack and 0 weight. 
PlayerCharacter's instances ma equip it.
- isPlayableCharacter() method, returns false if it's an enemy instance, true otherwise. It's used by the
waitTurn() method. It will be also used in future implementations.
- Equals() and hashcode() methods have a more suitable implementation depending on the class, complying with the
equals(O1) == equals(O2) <=> hashcode(O1) == hashcode(O2) equivalence. Characters are equal if they share the same
name, hp, atk in case of enemies, def, weight in case of enemies, equipped weapon in case of playable characters and
class. 

The test classes, as said before, follow a hierarchy too. For characters, it consists on an AbstractCharacterTest
test class that contains the testing methods of the common behavior for all characters, such as waiting for their
turn, the base constructor of the AbstractCharacter class (called by super, since it's not concrete) and some other 
methods detailed on the code. From this class emerge two testing subclasses, one for testing the Enemy class behavior (its 
weight, its constructor and how it waits for its turn); the other subclass, PlayerCharacterTest, tests the commom behavior of 
playable characters.
This class acts in a similar way to its super class, and makes use of its inherited methods to test part of the code. By 
the other hand, when checking the constructor, it creates an array to put the three available common-class instances of 
playable characters, then this array is uses by other testing methods to check the program works correctly. By last, from
this class emerges AbstractMageTest, with the purpose of testing the behavior of mages.

On a side note, the testing takes some time since the waitTurnTest() method delays the thread by some seconds, with every
repetition adding more time to it. Aside from that, the rest of the tests are pretty straightforward, consisting mostly on
constructors and equals/hashcode tests.

Lastly, there are, of course, a set of test classes for the model's weapons, built in a similar way to the character's testing
classes explained before. They are pretty straightforwad too, so it won't be necessary to explain further.

### Partial homework #3
Wasn't commited.

### Partial homework #4
The design was changed a bit following the feedback from T1's observations. PlayerCharacter evolved into AbstractPlayerCharacter,
since it was a necessary change to implement the new equip(), equals() and hashcode() methods that require specific instructions
depending on the subclass. In detail, hashcodes methods make use of a new string parameter (different for every subclass), so the
result when comparing objects from different classes keeps being coherent. By example, when comparing a Knight with an Engineer, they
may have the same name, equipped weapon and stats, but their unique string parameter makes them different, so comparing the hashcodes of
both characters' still returns false.
Tests were also changed, because it's not possible to instance an AbstractPlayerCharacter object anymore.
The getType() and getCharacterClass() methods, related to weapons and characters respectively, were deleted since they can't be
part of a good design. This had, as a consequence, the elimination of both enum classes. Every class/object "already knows" its type, and 
Double Dispatch methods will make use of this in the next version of the program.

By last, there are some things to adjust for the next versions: the total coverage of the tests and a correct implementation of attack/equip methods.

### Entrega 2 (de ahora en adelante será en español, dado que es necesario priorizar el tiempo a utilizar)
Respecto al modelo, se hicieron pequeñas adecuaciones:
- Se añade método isMage() que retorna true o false, dependiendo de si el character es mago o no.
- Se añaden atributos propertyChangeSupport para los respectivos métodos que ligan los handlers a las notificaciones. En particular, se añaden los eventos 
knockOutEvent, que notifica al handler knockOutHandler cuando una unidad es derrotada; el evento timerEvent que notifica al handler TimerHandler sobre 
el momento en que el temporizador ejecutado por el método waitTurn() termina, y por último, el evento endTurnEvent, que notifica al handler endTurnHandler 
cuando un personaje acaba de atacar y termina su turno.
- Método addToQueue() implementa la notificación "timerEvent.firePropertyChange" que notifica a su handler cuando el temporizador termina.
- Método attack() implementa la notificación "endTurnEvent.firePropertyChange" que notifica a su handler cuando el personaje ataca y termina su turno.
- Método receiveAttack() implementa la notificación "knockedOutEevent.firePropertyChange" que notifica a su handler cuando el personaje muere.
- Null Weapon implementa singleton pattern.
- El equipamiento de armas implementa double dispatch, con el fin de respetar los principios SOLID. La clase AbstractPlayerCharacter implementa el método equip(),
definido particularmente en cada subclase. Por ejemplo, para la clase EngineerCharacter, el método es implementado de la siguiente forma:

---
@Override
    public void equip(@NotNull IWeapon weapon) {
        weapon.equipToEngineer(this);
    }
---

Como se puede ver, equip() llama al método equipToEngineer (colocando al personaje ingeniero como argumento) del arma en cuestión. Dicho método está definido en
cada subclase de la jerarquía originada en AbstractWeapon. Por ejemplo, en la clase Axe, está implementado de la siguiente forma:

---
@Override
    public void equipToEngineer(@NotNull EngineerCharacter engineer) {
        engineer.receiveWeapon(this);
    }
---

Donde se ve que el arma es recibida por el personaje mediante el método receiveWeapon(IWeapon weapon), siendo efectivamente equipada. Por el contrario, si 
EngineerCharacterse hubiese intentado equipar un objeto de la clase Staff, esto no habría resultado, dado que el método equipToEngineer llamaría a receiveWeapon
de la clase Staff, la cual no permitiría equipar dicha arma a tal clase de personaje. Como nota final, para la clase ThiefCharacter fue considerado el error en
el enunciado descrito por el auxiliar, por lo que dicha clase puede equipar objetos Knife, Sword y Bow.
- Para el ataque entre personajes, el método attack(ICharacter character) define interacciones de combate entre todos los personajes del juego, habilitando friendly
fire entre personajes comandados por el jugador o entre enemigos. Esto con el fin de programar código mucho más extensible a futuro. El método attack() es descrito en AbstractCharacter, donde asegura que las condiciones de combate sean suficientes (que los personajes no estén muertos de antemano ni que se ataquen a si mismos), luego
calcula el ataque respectivo con calculateAttack(), método abstracto implementado concretamente en Enemy y en AbstractPlayerCharacter. En Enemy considera el stat de atk
del objeto, y en AbstractPlayerCharacter, el stat de atk del arma equipada. Por último, se llama al método receiveAttack(), implementado directamente en AbstractCharacter,
donde se calcula la diferencia entre el daño recibido y la defensa del personaje.

Respecto al controlador del modelo MVC, se crea una clase Controller y otras clases auxiliares: los handlers TimerHandler, EndTurnHandler y KnockOutHandler.
Éstos son algo autoexplicativos dada su descripción en el párrafo anterior. Entrando en los detalles del controlador:

- Puede crear todo tipo de personajes y armas, con los métodos createEngineer, createEnemy, etc, recibiendo los parámetros respectivos (nombre, stats, etc). 
En el momento de su creación, se le asignan las variables necesarias para las notificaciones del patrón observer, que será explicado profundamente más adelante.
- Reconoce a quiénes pertenecen dichos objetos. Los personajes que no son enemigos, son guardados en una lista llamada Party. Por otro lado, los enemigos
son guardados en una lista separada, perteneciendo a la "CPU". Por criterio del estudiante, el máximo de personajes comandados por el jugador es 4 y el máximo de
enemigos es 8. Cada vez que un personaje es creado, se revisa que la lista respectiva permita agregar un personaje adicional si es que aún no está completa (métodos
addCharacterToParty o addEnemyToList). De lo contrario, no lo agrega.
- Puede conocer los stats y atributos de cada objeto creado (sus stats, armas equipadas, etc) con los métodos getAtk, getDef, etc. Puede retornar las listas de
personajes del jugador o de enemigos. 
- Implementa un inventario para las armas de jugador. El inventario no permite duplicados. Al momento de equipar un arma a un personaje, el controlador se asegura
de que el arma esté disponible en el inventario (éstas son añadidas a él al momento de ser creadas). Si no lo está, no lo equipa. Cuando es equipada, la quita del
inventario para que no pueda ser equipada al mismo tiempo por otros personajes. 
- Puede equipar armas a los personajes del jugador.
- Permite ataques entre personajes.

La implementación de las notificaciones (observer pattern) y sus handlers es la parte más compleja. Para comenzar:
- Fueron creados los handlers TimerHandler, KnockOutHandler y EndTurnHandler. Éstos fueron explicados más arriba. Cada uno maneja la respectiva notificación también
explicada anteriormente. Son instanciados por el controlador, entregándose como parámetro a sí mismo.

---
Controller { 
...
/* Handlers and related objects for notifications of the observer pattern */
    private final IHandler timerHandler = new TimerHandler(this);
    private final IHandler endTurnHandler = new EndTurnHandler(this);
    private final IHandler knockOutHandler = new KnockOutHandler(this);
... }

---

- Éstos handlers son asignados a cada personaje creado, mediante los métodos:

---

addKOEventListener(knockOutHandler);
addTimerEndedEventListener(timerHandler);
addEndTurnEventListener(endTurnHandler);

---

Luego de ésto, cuando reciben las notificaciones, son manejadas de la siguiente forma:

- La notificación proveniente del handler KnockOutHandler es recibida por el método onKnockedOutCharacter(ICharacter knockedOutCharacter), que revisa
si el personaje es del jugador o el enemigo. En función de ello, revisa la respectiva lista. Este método es llamado cada vez que un personaje es knockeado.
Si la lista revisada posee todos los personajes knockeados, se llama al método victory() o defeat(), dependiendo de si la lista era la Party del jugador o 
la de enemigos. El método victory() cambia el atributo status del juego, pasando de PLAYING a DEFEAT/VICTORY. Éste atributo actúa como indicador auxiliar
para conocer en qué se encuentra el juego, y también fue usado para efectos de testeo. Por último, imprime en la pantalla una frase en contexto del
juego ("ganaste" o "perdiste").

- La notificación proveniente del handler EndTurnEvent es recibida por el método onTurnEnded(ICharacter character), sacando del queue al personaje en cuestión,
y ejecutando su método waitTurn(), como describe la mecánica de turnos descrita en la sección 2.2 del enunciado de la tarea. 

- Por último, la notiticación proveniente del handler TimerHandler es recibida por el método onTimerEnded(ICharacter character). De esta manera, el controlador 
sabe que la cola ya no está vacía (para evitar programar busy-waiting), y así comience el turno del siguiente personaje, seleccionándolo de la cola.


Hay un par de métodos no descritos en el readme (mayormente getters), que son autoexplicativos al momento de revisar el código del controlador.
Todo lo descrito anteriormente está testeado en sus respectivas clases.

Por último, también hay una clase EnemyFactory y Turn con las respectivas Phases, que serán implementadas completamente en la siguiente entrega.


# Deployment

Downloading/cloning the project and opening it using IntelliJ IDE, then you can run the gradle build to
check the completeness of the tests.


# Versioning

For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

# Authors

* **Ignacio Slater** - *Initial work* - [islaterm](https://github.com/islaterm)
* **Rodrigo G. Oportot** - *Student's work* - [dodii](https://github.com/dodii)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

# License

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

# Thanks to
- Ephyy
