# ScrunchTime
An app to facillitate roommate communication

## About the App

Scrunch Time is a mobile application that allows the user to click a button to represent their mood. The buttons correspond with different colors and moods, which are as follows.
Red: Do Not Disturb
Purple: Knock First
Blue: Neutral
Green: Feeling Social
Yellow: Friends in the Dorm

When the user clicks a button, they update their status. They have a scrunchie image that changes color to correspond with their status.

The user also sees scrunchies representing their roommates. The color of their roommate's scrunchie corresponds with their roommate's mood. The app can support up to three other roommates.

This version of the app is a proof-of-concept. It doesn't yet connect to a server, which would allow the user's roommate scrunchies to change colors.
However, I've commented out code that could be used to communicate with a future server.

## Overview of the App
### MainActivity
Launches the Welcome Screen.
### WelcomeScreen
Allows the user to input information about themselves, one other roommate, and the room. Takes the user to Home Screen after they've input information.
### HomeScreen
Shows the user's scrunchie, the buttons to change status, roommates' scrunchies, and a button to go to the Info screen.
### InfoScreen
Gives some information about the app. Also has a button to allow the user to add a roommate.
## AddRoommateActivity
Allows the user to input information about another roommate. Only allows the user to add three roommates or less. Takes the user to Homem Screen after they've input information.
### RoommateModel
A model of a roommate. Stores data (ex, String roomate name, boolean isUser, int clientID)
## RoomModel
A model of a room. Stores data. (ex, RoommateModel user, ServerTranslator translator, ArrayList<RoommateModel> roomies)
## ServerTranslator
Translates client language into server language. The server isn't used in this version of the app.
It has a function to translate status into ints. It has another function to translate clientIDs to serverIDs, which are de-coupled

## Notes about current version
RoomateModel, RoomModel, and ServerTranslator objects implement Serializable so they can pass between activites.
Supports error-catching for if the user hasn't input all the information needed for WelcomeScreen or AddRoommateActivity
The layouts were only tested for one phone, used for testing

## Improvements in future versions
This application was made in less than three weeks for an independent study project, from someone with no previous backround in Android.
I've strived to make good design decisions to make the code extensible, and also comment my code thoroughly. However, there is still a lot of work to be done before this app could be deployable.
In no particular order:
- Get app to work with a server
- Get event handler (a background function supposed to update every 30 seconds) to work (see HomeScreen for more info)
- Get layouts to work/look good for many different types of phones
- Reference strings from strings.xlm instead of hard-coding strings
- Create an Info screen that scrolls and has improved functionality (allowing the user to edit their name/their roommate's name/their room name, allow roommates to customize the meaning of each color)

### Thank you for looking at the ReadMe! 
