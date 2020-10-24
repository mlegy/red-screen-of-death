# Red Screen Of Death

## What

A simple screen that is shown when your app gets crashed instead of the normal crash dialog.

It's very similar to the one in Flutter.


<img src="https://github.com/mlegy/red-screen-of-death/blob/main/art/sample.jpg" width="35%">


## Install:

`RedScreenOfDeath` is distributed via `JCenter`. Add the dependencies to your `build.gradle` file.

There is also a `no-op` implementation of the library that you can use to make it easier to strip it from your release builds.

In your  `build.gradle`:

```groovy
repositories {
  jcenter()
}

dependencies {
    debugImplementation 'com.melegy.redscreenofdeath:red-screen-of-death:0.1.0'
    releaseImplementation 'com.melegy.redscreenofdeath:red-screen-of-death-no-op:0.1.0'
}
```

In your  `Application`  class:

```kotlin
class MyApp : Application() {  
    override fun onCreate() {  
        super.onCreate()  
        RedScreenOfDeath.init(this)  
    }  
}
```

And you are done!