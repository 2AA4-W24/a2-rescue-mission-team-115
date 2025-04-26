[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/OY6OZuzi)
# [A2] Island ca.mcmaster.se2aa4.island.team00.Explorer

- Authors:
  - [Karim, Elbasiouni](elbasik@mcmaster.ca) 
  - [Omar, Abdelhamid](abdelo8@mcmaster.ca)
  - [Kun, Xing](xingk8@mcmaster.ca)

## Product Description

This product is an _exploration command center_ for the [Island](https://ace-design.github.io/island/) serious game. 

- The `ca.mcmaster.se2aa4.island.team_XXX_.Explorer` class implements the command center, used to compete with the others. (XXX being the team identifier)
- The `Runner` class allows one to run the command center on a specific map.

### Strategy description

The exploration strategy is for now to stop exploring as soon as we start. We stay safe and fly back to base immediately.

## How to compile, run and deploy

### Compiling the project:

```
mosser@azrael a2-template % mvn clean package
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.960 s
[INFO] Finished at: 2024-01-20T18:26:43-05:00
[INFO] ------------------------------------------------------------------------
mosser@azrael a2-template % 
```

This creates one jar file in the `target` directory, named after the team identifier (i.e., team 00 uses `team00-1.0.jar`).

As the project is intended to run in the competition arena, this jar is not executable. 

### Run the project

The project is not intended to be started by the user, but instead to be part of the competition arena. However, one might one to execute their command center on local files for testing purposes.

To do so, we ask maven to execute the `Runner` class, using a map provided as parameter:

```
mosser@azrael a2-template % mvn exec:java -q -Dexec.args="./maps/map03.json"
```

It creates three files in the `outputs` directory:

- `_pois.json`: the location of the points of interests
- `Explorer_Island.json`: a transcript of the dialogue between the player and the game engine
- `Explorer.svg`: the map explored by the player, with a fog of war for the tiles that were not visited.

### Deploying the project to the arena

Each week, you'll have to `tag` the version you want to submit for the competition. This version will be used in the weekly run. No tag means no competition.

The tag syntax is `wX`, with `X` the week number. So your product for the first week will be tagged `w1`.

## Technical Details

### Project Structure
```
src/main/java/ca/mcmaster/se2aa4/island/team115/
├── Action.java           # Action handling and execution
├── BatteryTracker.java   # Battery management system
├── Coordinates.java      # Coordinate system implementation
├── Direction.java        # Direction enumeration
├── Drone.java           # Drone control and navigation
├── Explorer.java        # Main exploration logic
├── GridSearcher.java    # Grid-based search algorithms
├── Info.java            # Information processing
├── IslandFinder.java    # Island detection and mapping
├── POIFinder.java       # Point of Interest detection
├── POIMap.java          # POI mapping and management
├── Runner.java          # Application entry point
└── Translator.java      # Command translation
```

### Technical Stack
- **Language**: Java 21
- **Build Tool**: Maven
- **Testing Framework**: JUnit 5
- **Logging**: Log4j 2.22.1
- **Dependencies**:
  - Island Framework (Player & Runner) v3.0
  - Apache Commons CLI
  - JSON Processing
  - Mockito (for testing)

### Key Features
1. **Autonomous Navigation**
   - Grid-based exploration algorithm
   - Efficient path planning
   - Collision avoidance

2. **Resource Management**
   - Battery level tracking
   - Resource collection optimization
   - Safe return path calculation

3. **Mapping System**
   - Real-time island mapping
   - POI detection and tracking
   - Coordinate system management

4. **Safety Features**
   - Battery level monitoring
   - Emergency return protocols
   - Boundary detection

### Testing
The project includes comprehensive test coverage using JUnit 5 and Mockito. To run tests:
```bash
mvn test
```

### Documentation
The project includes several UML diagrams for better understanding:
- High-level class diagram
- Sequence diagrams
- Map representation diagrams

### License
This project is licensed under the terms specified in the LICENSE.txt file.

### Acknowledgments
- McMaster University SE2AA4 Course Team
- ACE Design Lab for the Island Framework