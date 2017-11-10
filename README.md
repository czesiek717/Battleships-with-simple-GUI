# Battleships-with-simple-GUI
It's one of my first projects.

This battleships game bases on JButtons instead of any custom graphics and fields. This game was more of a algorithm practice then graphics presentation.
Game lacks "new game" option, basically it has to be turned off and on again to play a new game. This is an unfinished project. 
Only game type vs AI works proberly. Other game types have been disabled through code, although some progress into making this game 1v1 on 
same machine can be found inside. Ready to run .jar game file can be found in out\artifacts\Battleships_jar.

AI has three difficulties:

- easy - always shoots at random places

- medium - shoots at random palces until it finds any ship. Then shoots around that place to find more parts of the ship. If found determines ships position (horizontal or vertical) and proceeds to shoot until the ship is destroyed. If any other part of the ship hasn't been found resets the algorithm and starts from the beginning.

- hard - offers everything at medium difficulty does + it knows the max lengh of the ships that has been shot so it doesnt look for a full 4 lenght ship when it has been destroyed, instead looks for the second max ship (in this case 3). Additionally after destroying a hip it knows that there can't be any ship placed near it, so it doesnt look there.

Example image:


![alt text](https://raw.githubusercontent.com/czesiek717/Battleships-with-simple-GUI/master/res/example.png)
![alt text](https://raw.githubusercontent.com/czesiek717/Battleships-with-simple-GUI/master/res/example2.png)
