# simple-config-v0
A java config library.

## Add dependency
[![](https://jitpack.io/v/TCBuildMC/SimpleConfigApi.svg)](https://jitpack.io/#TCBuildMC/SimpleConfigApi)

Add these to your `build.gradle`:
```gradle
repositories {
    maven {
        url = "https://jitpack.io/"
    }
}

dependencies {
    implementation "com.github.TCBuildMC:SimpleConfigApi:{VERSION}"
}
```

Remember to replace `{VERSION}` with full version.

## How to use
The latest version (1.2.x) javadocs can be found [here](https://jitpack.io/com/github/TCBuildMC/SimpleConfigApi/latest/javadoc/).

### Example
```java
// Read your config file:
YourConfigClass config = SimpleConfigApi.getInstance().read(YourConfigClass.class, yourConfigFile, DefaultParsers.<name>());

// do stuff...

// Write to your config file:
SimpleConfigApi.getInstance().write(YourConfigClass.class, config, yourConfigFile, DefaultParsers.<name>());

// Get a ConfigObject of your config file:
ConfigObject object = SimpleConfigApi.getInstance().read(yourConfigFile, DefaultParsers.<name>());

// Read a value of your config file by ConfigObject:
var yourConfigValue = object.get("yourConfigKey");

// Delete a value of your config file by ConfigObject:
object.set("yourConfigKey", null);

// Replace / Add a pair of config key and value:
object.set("yourConfigKey", yourConfigValue);

// Save your config file by ConfigObject:
SimpleConfigApi.getInstance().write(config, yourConfigFile, DefaultParsers.<name>());
```

## Building
This project is compatible with Java 8 at least, so please use Java 8 to build.

We also prefer to use IntelliJ IDEA to build.

Download the zip and run `gradlew build --stacktrace --no-daemon -xtest`.

The artifacts will be in `build/libs/` directory.

## License
This project uses Apache 2.0 License. Feel free to use it in your project.
