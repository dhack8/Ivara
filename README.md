# Team Ivara/11 Readme
Feel free to add to this readme, just make sure it's pretty.
## Role Allocations
| Library | Classes | Primary Author | Code Reviewer | External Tester |
| ------ | ------ | ------ | ------ | ------ |
| 1) Render | ProcessingRenderer, Sprite Component, AnimatedSpriteComponent | David | James | Callum |
| 2) Control | ScriptComponent, Controller | Will | David | Alex |
| 3) Scene | Scene, Entity | Alex | Callum | David |
| 4) Physics | ColliderComponent, PhysicsComponent, PhysicsEngine | Callum | Will | James |
| 5) Assets | ProcessingConsole, Game, AssetHandler, InputHandler | James | Alex | Will |
| Misc |Ivara | All | All | All|
## Libraries and Utilities
### Libraries
Processing will be used for rendering the game and the main game loop inside a Swing component.
Swing is most likely going to be used for menus with a modified look and feel.
Possibly JBox2d in the future for more complicated physics.
### Utilities/Resources
Using Gradle to build the project to be able to program easily in different enviroments and computers.
Game assest can be found here https://kenney.nl/assets

## Installation and Setup
Just run these commands and you should get the repo and a red window showing up.
If you have any problems just send me a cheeky pm (Callum Li).

If you are on a unix based system:
```
cd <whatever directory you want to work in>
git clone https://github.com/Raveva/swen222-project.git
./gradlew build
./gradlew run
```

Windows
```
dir <whatever directory you want to work in>
git clone https://github.com/Raveva/swen222-project.git
gradlew build
gradlew run
```

From there, you can use your IDE of choice to edit it.
I know IntelliJ IDEA has great support for gradle projects, you can just open
the build.gradle. Not sure about Eclipse though.

Any questions hmu (Callum Li).

## Class Diagram
![Click here](https://gitlab.ecs.vuw.ac.nz/hackdavi/temp-group-project/blob/master/classDiagram.png)