# 478Proj3

3 Separate applications. Each application uses a custom permission and explicit intents to call each other. 
App A1 calls A2 only if holding the requisite permission, and A2 calls A3 only if it has the same permission.
App A3 Contains a listview with 3 fragments representing phone models. The fragments don't work exactly right...

App A3 uses an ordered broadcast with the chosen list item to A2 and from A2 to A1 which opens the corresponding
webpage for the phone selected
