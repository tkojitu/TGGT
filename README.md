TGGT: Teenage Gesture Generative Turtles
========================================

TGGT generates gesture data that android.gesture.GestureLibrary can load.

TGGT is an interpreter for turtle graphics. TGGT saves diagrams as gesture data.

TGGT Language
---------------

| Command   | Description                            |
| --------- | -------------------------------------- |
| d         | pen Down                               |
| m times   | Move forward n times (n is an integer) |
| n name    | gesture Name                           |
| s ppf     | Set pixels per frame in Move           |
| t degrees | Turn degrees clockwise (d is a float)  |
| u         | pen Up                                 |
| w x y     | Warp at x,y (x and y are floats)       |

Examples
--------

```
n rectangle
w 100 100
d
m 100
t 90
m 100
t 90
m 100
t 90
m 100
u
```

GestureChecker
---------------

You can check the gesture data by using [GestureChecker](https://github.com/tkojitu/GestureChecker).

License
-------

TGGT is licensed under [CC0](https://creativecommons.org/publicdomain/zero/1.0/).
