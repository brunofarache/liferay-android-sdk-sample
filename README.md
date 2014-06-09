![Liferay Mobile SDK logo](https://github.com/liferay/liferay-mobile-sdk/raw/master/logo.png)

## Liferay Android SDK Sample

This is the sample application for the
[Liferay Android SDK](https://github.com/liferay/liferay-mobile-sdk/tree/master/android).

It's a simple app that displays users in a table view. By clicking on an user,
it will display its contact details such as name, email, birthday and phones.
By default, it uses the following credentials to authenticate:

Email: `test@liferay.com`; password: `test`; server: `http://10.0.2.2:8080`.

You can change these values in the
[SettingsUtil.java](src/com/liferay/mobile/sample/util/SettingsUtil.java) class.

Most of the interesting code are in
[MainActivity.java](src/com/liferay/mobile/sample/activity/MainActivity.java)
and
[DetailsActivity.java](src/com/liferay/mobile/sample/activity/DetailsActivity.java)
.
In these classes you can see how to create sessions and call portal services.

If you want to make asynchronous requests, take a look on how
[ContactCallback.java](src/com/liferay/mobile/sample/task/callback/ContactCallback.java)
is used. If you have an existing `AsyncTask` and want to use that to make
services calls, take a look at
[UsersAsyncTask.java](src/com/liferay/mobile/sample/task/UsersAsyncTask.java).

### Import into your IDE

#### Android Studio

1. Click on `Import Project…`

2. Choose `buid.gradle` from the root folder

3. And that's it, Android Studio should automatically use Gradle Wrapper and
create a `local.properties` file for you. 

### Build

This project uses Gradle as the build and dependency management system. If you
don't want to use Gradle, you can download the Android SDK JAR and its
dependencies and copy them to your project's `/libs` folder, follow the
instructions described [here](https://github.com/liferay/liferay-mobile-sdk/tree/master/android#manually).

In order to build with Gradle, you have to:

1. Create a local.properties files with a `sdk.dir=` property pointing to your
Android SDK folder.

2. Run `$ ./gradlew build` from the project root folder. This will
automatically download the Android SDK and dependencies for you.

There are many more useful tasks, for example, for running your project in an
emulator or device. Run `$ ./gradlew tasks` to see which tasks are available.

For more information on how the Android Gradle plugin works, read their
[documentation](http://tools.android.com/tech-docs/new-build-system/user-guide).