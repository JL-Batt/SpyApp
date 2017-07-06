# SpyApp
Spy App developed as group project for our Mobile and Embedded Devices module at University.

Authors - Jesse Batt, Laurence Burton, Joseph Friend, Billy Cuthbert

Last Modified - 24/03/2017

# Spy App Overview
This app was developed for an assignment project and had to meet the following specification.
In addition to this, it's the first mobile application I/we had ever worked on, so please excuse
any aspects that are rough around the edges...

"This assignment will involve you writing an Android app with a login interface and security 
data storage (encrypted data) to store the username and password. Once the user logs in the 
Spy App will allow recording of audio and the capture of still images using the built-in 
microphone and camera." 

(Full Assignment Specification can be found here -> https://drive.google.com/open?id=0BzULiE3B5f3ZM0JoUDN2UHVMSFE)

The purpose for the app is to essentially allow for secure users to login to the app and be
able to capture still images and audio in a descrete manner. We opted to cover the conventional
camera user interface with a dummy Google search screen. We added some additional features, such
as username and password encryption using Salt (see PHP files), as well as some more subtle features
such as live camera rotation.

For our first time developing a Mobile Application and our first real experience with Java, this turned 
out pretty well - long days, late nights and a LOT of googling... It must have paid off, as we ended up scoring 
full marks for the assignment.

# Software Requirements
1. Android Studio
2. Local server hosting software (ie. XAMPP/WAMP/MAMP)
3. Android Device to run/test application on

# Instructions
1. Existing users on your server - enter your username and password and Login.
   If you like, you can change your password to something different too.
   New users on your server - tap Register and create your username and password,
   you will then be able to Login.
   
2. Once logged in, you will be greeted with a default, simple Camera user interface,
   which will be recording audio from the moment you login (for all that Spying mischief)
   and will allow you to take still images with the Capture button.
   
3. Should you wish to take photos and record audio in a more descrete way, you can tap the Hide
   button, which will overlay your screen with a dummy Google screen. Don't worry, you will 
   still be recording audio, and you can capture images by tapping the Google Sign In button.
   Sneaky, right?
   
4. To bring back the default Camera Interface, just tap the blue Google Search button 
   on your screen.   

# Testing Requirements
The Spy App uses PHP/MySQL in order to store all user login details within a SQL database.
For this to work, you will need to set up a few things external to the application itself 
before running the app on a device.

1. You must be running an Apache server with phpMyAdmin and MySQL working. 
   Windows - XAMPP / WAMP
   Mac OSX - MAMP / MAMP PRO
   
2. You must have the PHP files in the appropriate directory for 
   use with your app and server. These are all located in the php 
   directory of this repo, so you can just copy them over to where they're needed.
   
3. The SQL script used to create the database that contains user details can be found in
   "spyappusers.txt"
   
4. When running/testing the app on a physical Android device, you must activate 
   personal WiFi hotspot mode on the device, and connect the development 
   machine (laptop/PC etc) to the Android device over WiFi. 
   
   (Note - while this isn't practical, it does allow for the login/register/change 
   password features to be demonstrated.)
   
# Notes

It's been a while since I've been able to properly look at this, seeing as we submitted 
the project at the end of March, and it's July at the time of upload, but I'll be fixing 
any bugs I find and add comments over time. 

Also, tap the Spy Icon on the Main screen 5 times for a little tune...
   
   
   
