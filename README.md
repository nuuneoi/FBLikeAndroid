# FBLikeAndroid
A wrapper for Facebook Native Like Button (LikeView) on Android

![](http://inthecheesefactory.com/uploads/source/facebooklike/fblikeandroid.png)

# Version

1.0.1

# Installation

To use this library in your android project, just simply add the following dependency into your build.gradle

```sh
dependencies {
    compile 'com.inthecheesefactory.thecheeselibrary:fb-like:1.0.1'
}
```

# Usage

Full document is available at http://inthecheesefactory.com/blog/how-to-add-facebook-like-button-in-android-app/en

First of all, you have to setup the create a Facebook App and setup Facebook SDK for Android in your Android App. Please check [this link](http://inthecheesefactory.com/blog/how-to-add-facebook-like-button-in-android-app/en).

After that, place `com.inthecheesefactory.lib.fblike.widget.FBLikeView` wherever you want to place a Facebook Like Button, for example:

```xml
<TextView
    android:id="@+id/tvUrl"
    android:text="@string/hello_world" android:layout_width="wrap_content"
    android:layout_height="wrap_content" />

<com.inthecheesefactory.lib.fblike.widget.FBLikeView
    android:id="@+id/fbLikeView"
    androi:text="Login to Like"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```

You could get a LikeView instance through `getLikeView` method.

```java
FBLikeView fbLikeView = (FBLikeView) rootView.findViewById(R.id.fbLikeView);
LikeView likeView = fbLikeView.getLikeView();
```

To set a url and its type, you could do via LikeView's `setObjectIdAndType` method.

```java
fbLikeView.getLikeView().setObjectIdAndType("YOUR_URL_HERE", LikeView.ObjectType.OPEN_GRAPH);
```

And to connect every single FBLikeView to Facebook Login process, you have to add this line to every single Activity's `onActivityResult` in your app.

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    FBLikeView.onActivityResult(requestCode, resultCode, data);
}
```

That's all. LikeView will now work perfectly in your app !

To disconnect your app from Facebook, just simply use this command:

```java
FBLikeView.logout();
```

# Change Logs

### v1.0.1

Fix minor bug

### v1.0.0

Update Facebook SDK version to v4.4.0

### v0.9.3

Initial version

# License

Apache 2.0
