# Build Instructions

This mod uses the Gradle build system with Fabric Loom.

## Prerequisites
- JDK 21 or higher
- Git (optional)

## Building the mod

### Windows
```batch
gradlew.bat build
```

### Linux/Mac
```bash
./gradlew build
```

## Output

The built JAR file will be located in:
```
build/libs/elemental-dimensions-1.0.0.jar
```

## Development

To set up a development environment:
```
gradlew.bat genSources
```

To run the mod in a development environment:
```
gradlew.bat runClient
```

## Clean build

To clean previous builds:
```
gradlew.bat clean
```

## Full rebuild

To perform a complete clean rebuild:
```
gradlew.bat clean build
```
