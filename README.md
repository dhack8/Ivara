## Installation and Setup

### Cloning the repository
**For cloning with https**
```
git clone https://gitlab.ecs.vuw.ac.nz/swen422-2019-a2/t20/pablo.git
```
**For cloning with ssh**
```
git@gitlab.ecs.vuw.ac.nz:swen422-2019-a2/t20/pablo.git
```

### Installing Gradle
This project utilizes Gradle to simplify dependency resolution and build management across varying setups.

The current release of Gradle can be downloaded at https://gradle.org/next-steps/?version=5.6.2&format=all, alternatives can be selected from https://gradle.org/releases/.

**For Windows based systems:**

Create a directory named "Gradle" in the C drive `C:\Gradle`.

Extract the content folder `gradle-5.6.2` into the `C:\Gradle` directory from the archive file linked above.

Add a new PATH entry in the system environment variables for `C:\Gradle\gradle-5.6.2\bin`.

### Setting up the project in IntelliJ
The process for opening the project in IntelliJ is simple.

Open IntelliJ. Select open project. Select the `build.gradle` file of Ivara.

The tasks for building and running the project can be found in the gradle panel to the right of the UI.

### Running the project through the command line

**For Windows based systems:**

(From within the project directory)
```
gradle build
gradle run
```

### Other
Game assets can be found here https://kenney.nl/assets