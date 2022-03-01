# SlantedTextCompose
Slated Text for Jetpack Compose

## Preview
[]: /docs/art/screenshot_1.png

## Adding dependency
1. Add jitpack.io as your dependency repository
```groovy
    allprojects {
        repositories {
            //...
            maven { url 'https://jitpack.io' }
        }
    }
```
2. Import this library
```groovy
  dependencies {
    //...
    implementation 'com.github.slantedtext:slantedtext-jetpack-compose:0.1.0'
  }
```

## Quick start
```kotlin
SlantedText(
    text = "Slant",
    textSize = 15.sp,
    bold = false,
    thickness = (width * 100f).dp,
    padding = 16.dp,
    slantedMode = SlantedMode.TOP_RIGHT
) {
    // Your content, a box here
    Box(
        modifier = Modifier
            .size(150.dp, 100.dp)
            .background(Color.Blue)
    )
}

```

## License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)