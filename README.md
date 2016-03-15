# SocialNetworkingKata
This is a kata to implement a console-based social networking application with a few features (posting, viewing timelines, following and viewing walls).

## How to compile and run the application

### Console mode (for Mac users and Linux users)
**NOTE:** This program needs [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven 3](https://maven.apache.org/download.cgi) to be compiled. In case you don't have them installed, please do it.

1) Clone the repository
```bash
$ git clone https://github.com/srodrigo/SocialNetworkingKata
```

2) Compile the application
```bash
$ cd SocialNetworkingKata
$ mvn compile
```

3) Run the application
```bash
$ cd target/classes
$ java me/srodrigo/socialnetworkingkata/Main
```

NOTE: On Linux, the command might be:
```bash
$ java me.srodrigo.socialnetworkingkata.Main
```

4) To stop the application, press CTRL+C

Optional - To run the tests:
```bash
$ mvn test
```

### Graphical mode (Using [Intellij](https://www.jetbrains.com/idea/) 15)

1) Import the project

File -> New -> Project from Version Control -> Git -> https://github.com/srodrigo/SocialNetworkingKata

You may need to set the JDK before compiling.

2) Run the application

To run the application, right click on the Main.java file from the Project view, and select Run 'Main.main()'

To run the tests, right click on the project root, and select Run 'All Tests'
