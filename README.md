# BomberMan [![CircleCI](https://circleci.com/gh/facebook/litho/tree/master.svg?style=svg)](https://circleci.com/gh/facebook/litho/tree/master) [![Join the chat at https://gitter.im/facebook/litho](https://badges.gitter.im/facebook/litho.svg)](https://gitter.im/facebook/litho?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

<img src="docs/static/logo.png" width=150 align=right>

BomberMan is a open source project to every one new in java and oop programming.
you can play run this project in 4 mode:

* **Desktop in personal computer:** in this mode you can run game in a pc that 4 player can play togather in wireless or wired network.
* **one desktop and mobile application as gaming controller :** in this mode Bomberman game run in a computer as server and four mobile application can connect to game program in client mode as gaming controller by wifi wireless network.
* **online gaming in web platform:** in this mode we develop an online bomberman game that 4 player can play together in internet connection .
* **mobile application:** One mobile application that 4 player can play together in wifi or internet network.

To get started, check out these links:

* [Learn how to use Litho in your project.](http://fblitho.com/docs/getting-started)
* [Get started with our tutorial.](http://fblitho.com/docs/tutorial)
* [Read more about Litho in our docs.](http://fblitho.com/docs/intro)

## Installation
Litho can be integrated either in Gradle or Buck projects. Read our [Getting Started](http://fblitho.com/docs/getting-started) guide for installation instructions.

## Quick start
### 1. Initialize `SoLoader` in your `Application` class.
```java
public class SampleApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, false);
  }
}
```
### 2. Create and display a component in your Activity
```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final ComponentContext context = new ComponentContext(this);

    final Component component = Text.create(context)
        .text("Hello World")
        .textSizeDip(50)
        .build();

    setContentView(LithoView.create(context, component));
}
```
## Run sample
You can find more examples in our [sample app](https://github.com/facebook/litho/tree/master/sample).

To build and run (on an attached device/emulator) the sample app, execute

    $ buck fetch sample
    $ buck install -r sample

or, if you prefer Gradle,

    $ ./gradlew :sample:installDebug

## Contributing
For pull requests, please see our [CONTRIBUTING](CONTRIBUTING.md) guide.

See our [issues](https://github.com/facebook/litho/issues/) page for ideas on how to contribute or to let us know of any problems.

Please also read our [Coding Style](http://fblitho.com/docs/best-practices#coding-style) and [Code of Conduct](https://code.facebook.com/codeofconduct) before you contribute.

## Getting Help

- Post on [StackOverflow](https://stackoverflow.com/questions/tagged/litho)
  using the `#litho` tag.
- Chat with us on [Gitter](https://gitter.im/facebook/litho).
- Join our [Facebook Group](https://www.facebook.com/groups/litho.android/) to
  stay up-to-date with announcements.
- Please open GitHub issues only if you suspect a bug in the framework or have a
  feature request and not for general questions.

## License

Litho is licensed under the [Apache 2.0 License](LICENSE).

