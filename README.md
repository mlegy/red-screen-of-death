# Red Screen Of Death

## What

A simple screen that is shown when your app gets crashed instead of the normal crash dialog.

It's very similar to the one in Flutter.


<img src="https://github.com/mlegy/red-screen-of-death/blob/main/art/sample.gif" width="35%">


## Install:

`RedScreenOfDeath` is distributed via `JCenter`. Add the dependencies to your `build.gradle` file.

There is also a `no-op` implementation of the library that you can use to make it easier to strip it from your release builds.

In your  `build.gradle`:

```groovy
repositories {
  jcenter()
}

dependencies {
    debugImplementation 'com.melegy.redscreenofdeath:red-screen-of-death:0.1.1'
    releaseImplementation 'com.melegy.redscreenofdeath:red-screen-of-death-no-op:0.1.1'
}
```

In your  `Application`  class:

- Kotlin
```kotlin
class MyApp : Application() {  
    override fun onCreate() {  
        super.onCreate()  
        RedScreenOfDeath.init(this)
        // Or initRedScreenOfDeath()
    }  
}
```

- Java
```java
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RedScreenOfDeath.init(this);
    }
}
```

And you are done!
