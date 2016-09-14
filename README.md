# JARGON - v0.0.1

## JARGON?
JARGON stands for: Java Assisted, Retro Game, Opensource Namespace

## The Purpose
The main purpose of JARGON is to give anyone the ability to create and design beautiful 2D games with a highly polished editor. 

## How to Download & Run
###Eclipse
1. `cd` into the directory of where you would like to download JARGON
2. Clone the Jargon repo: `git clone https://github.com/nlogm/Jargon`
3. Go into Eclipse
4. Click `File` -> `import` -> `Gradle Project` -> Enter path to where you downloaded JARGON
5. Run the program from Desktop Launcher, if there are errors go to Step 5
6. Click `Run` -> `Run Configurations`
7. Highlight your build (May be "DesktopLauncher")
8. Click `Arguments`
9. Under `Working Directories:"`, select `other`
10. Paste this `${workspace_loc:IntroScreen-desktop/assets}` into the `other` space

###Terminal
1. `cd` into the directory of where you would like to download JARGON
2. Clone the Jargon repo: `git clone https://github.com/nlogm/Jargon`
3. `cd Jargon`
4. `sudo chmod +x gradlew`
5. `./gradlew desktop:run`

If you run into further issues [Please click here](https://github.com/nlogm/Jargon/issues) and explain any issues you are having. You can also email any contributor on this project.




## Features
- [ ] Layered Map Editor
- [ ] Light Editing
- [ ] Physics Body Editor
- [ ] Physics Body Image Molder
- [ ] Image Manipulation
- [ ] LibGDX Level Parser
