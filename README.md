# SlantedTextCompose
| [中文说明](docs/README_CN.md) | 

Slated Text for Jetpack Compose



## Preview
![](/docs/art/screenshot_1.png)

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
    implementation 'com.github.re-ovo:slanted-text-compose:1.0.0'
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
```text
MIT License

Copyright (c) 2022 RE-OVO
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
