# theDefendor
This is a top to down shooting game developed in java

🎮️ Name of the game: The Defender
👥 Stakeholders:
•	Developer: Marc Hypolite

# Gameplay
The game will be a top too down shooter which means the player will see the character on the screen as though he was looking at the character from above. There will be a main character that holds a gun that shots fireballs this gun will be pointed at whatever direction the player moves his/her mouse too, the character will be moved using the ‘W’,’A’,’S’,’D’ keys as the arrow keys where w is up s is down a is left and d is right. the player will start off with 100 health points and 0 kill points this information will always be displayed at the top of the screen. To kill an enemy the player must hit that enemy with a certain number of fireballs until the enemy dies. Basic enemies die after being hit 10 times and the boss enemy will die after being hit 100 times.  every time the player kills an enemy, they will gain 1 kill point and every time the enemy hits the player will lose 5 health points. the player must try to stay away from the enemy's melee attacks while attacking the enemies with his gun. The player will press any mouse button the fire the gun in the direction that the player character is facing. On the first level of the game three enemies will be randomly spawned on the map and run towards the player’s character they would randomly swing they weapon too appear more threatening while doing so. If any of these basic enemies collide with each other one of them will ‘teleport’ too another random location on the map this is done too prevent the enemies from clustering in the same exact location and also too make the game a little more interesting. When enemies ‘teleport’ a puff of purple smoke will appear concealing the enemy as he teleports away. After these three enemies have been killed the game ill then spawn 5 new basic enemies that the player must defeat this will be round or level 2 of the game. This level will be more difficult than the last since the player’s health will not be reset and they would likely have to defeat more enemies with less health and less space to run away. After these 5 enemies have been defeated the game will start level or round 3 in which the player will have too defeat a ‘boss’ enemy. This enemy will have a different design and animations from the basic enemies. This enemy would be considerably larger than the basic enemies too appear more threatening than them. If the boss enemy collides with the player, it will do an attack that will take twenty health points from the player. This enemy should be more difficult to defeat because its attacks are strong and it takes more hits too defeat it. After the ‘boss’ is defeated the player would have won the game and a congratulations message will be printed on the screen. If at any time the player’s health points reaches below 0 the player will lose the game and a message will be printed on the screen. when the player is moving downwards the map will also move giving the effect than the player has walked further down the road on the background image. The player cannot move the character off screen with the W,A,S,D keys.


## Core Game Mechanic #1
* Details: Animations
* How it works: The game objects besides the map itself will be animated. The player character’s movement and shooting, the enemy’s movement and attacking and the boss’s movement and attacking will all have animations.
## Core Game Mechanic #2
* Details: Game physics
*	How it works: The characters rate of fire will be modeled in such a way where when the player shoots it will start of at a certain speed which will decrease over time due to drag.
## Core Game Mechanic #3
*	Details: collection detection 
*	How it works: The game will use collection detection too check if the enemies reach hit the player’s character, A fireball hits an enemy or an enemy comes in contact with another enemy


## Core Game Mechanic #4
*	Details: Health and kill information
*	How it works:  The player’s character known as the ‘the defender’ will start off with 100 health points and 0 kill points. Health points will determine weather the payer loses the game if a player is attacked by a basic enemy they lose 5 health points if they are attacked by the boss they lose 20 health points if the player’s health points reach under 0 they lose the game. The player’s health and kill points will always be displayed at the top of the screen for the player too gauge how they are doing in the game.
## Core Game Mechanic #5
*	Details: Background Scroll
*	How it works: The player can move ‘down’ the street in this game but not upwards. As the player moves in a downward direction the background will scroll up revealing a duplicated image of the background giving the appearance that the player is moving down the street.
# Game elements
Describe your game world, including all the characters, location, object, and other elements in it.
# 👤 Characters
*	The main character which the player controls will be a futuristic soldier using a gun that shots fire balls. This character is known as The Defender.
*	The basic enemies will also be futuristic soldiers using electric batons too attack the player.
*	This Boss Enemy will be a Giant Robot that attacks the player using it’s robotic arm.

# 🗺️ Locations
*	The map will be an open space like a field where the enemies can come from any direction when the player moves downward the map will ‘scroll’ downwards vertically too appear as though the player walked down the street.
🏆️ Levels / missions
*	Level 1: In this level the game will spawn 3 basic enemies that the player must defeat in order too move on too level 2.
*	Level 2: this level will start as soon as the player defeated the 3 enemies from level 1 this level will spawn 5 enemies for the player too defeat before they can move on too level 3.
*	Level 3: This level is the most difficult in the game. In this level the Boss Enemy will be spawned which the player must defeat in order too win the game.
# 📦️ Objects
*	Player (Defender) 
![image](https://user-images.githubusercontent.com/60365043/126704071-41f36718-a986-4def-b92e-301f1bf6ef8c.png)

*	Fireball
![image](https://user-images.githubusercontent.com/60365043/126704054-8cfa25e2-4419-41f1-a94a-88dd18280bde.png)
 
*	Map
![image](https://user-images.githubusercontent.com/60365043/126704028-a95b31fd-b717-42a6-858d-40fb89526fcb.png)
 
*	Enemy
![image](https://user-images.githubusercontent.com/60365043/126704259-1413d5d9-42f7-4c8f-ac6a-2468570e2d55.png)

*	Boss Enemy
![image](https://user-images.githubusercontent.com/60365043/126704271-82ffa038-b3e8-4b4d-9100-f969aacb4bd2.png)

# Assets 
Here is where you will include all of the assets needed as well as brief descriptions.
## 🎨 Art
*	Player character
*	Enemy character
* map
## 🔊 Sound
*	Fireball sound effect
*	Basic enemy dieing sound
*	Baton swinging sound effect
*	Background music
*	Defender saying “I am the law here” at the start of game
*	Robot turning on sound effect
*	Robot body moving sound effect
*	Robot getting hit sound effect
*	Giant Robot walking sound effect

## 🏃‍ Animation
*	Walking animations for characters
*	Shooting animations for main character
*	Batton swinging animation for enemy characters
*	Arm swinging animation for Boss enemy
*	Animation for basic enemy getting hit by a fireball.

# preliminary object-oriented model
* ![image](https://user-images.githubusercontent.com/60365043/126704560-60939eca-565c-4637-8b25-02ba6f272904.png)

# Youtube demo links
* https://www.youtube.com/watch?v=anXl_geYYxw&ab_channel=MarcHypolite


 
