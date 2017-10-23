# StatusView [![](https://jitpack.io/v/wenqin-231/StatusView.svg)](https://jitpack.io/#wenqin-231/StatusView)

This is a library to help you show the different status of view such as loading , empty and error easily.

### Features

* Support both in Activity and Fragment.
* Support the custom in different status of view.
* Support error view to retry request by default button.
* Easily use. 
* With a delay loading avoid the too fast loading.
* Support the Toolbar、NavigationBottomBar and etc.
* Can use it with [my forked SpringView]("https://github.com/wenqin-231/SpringView") gracefully.

### Demo

[DownLoad Demo](http://fir.im/StatusView)

* Loading with dialog

  ![](https://github.com/wenqin-231/StatusView/blob/master/art/status_view_dialog.gif?raw=true)

* Loading error

  ![](https://github.com/wenqin-231/StatusView/blob/master/art/staus_view_error.gif?raw=true)

* Loading empty

  ![](https://github.com/wenqin-231/StatusView/blob/master/art/status_view_empty.gif?raw=true)

### Usage

* Use it in with `BaseStatusActivity` or `BaseStatusFragment` :

```
public class DemoMainActivity extends BaseStatusActivity {
    // use it just by two line of code
	public void onLoadingClick(View view) {
		mStatusView.setStatus(StatusView.Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(StatusView.Status.NORMAL);
			}
		}, 2000);
	}
}
```

* Use it without `BaseStatusActivity` or `BaseStatusFragment` :

```
mStatusView = StatusView.initInActivity(this);
// setup btn click in Error View
mStatusView.setOnRetryBtnClickListener(new OnRetryBtnClickListener() {
	@Override
	public void onClick(View view) {
		onNormalLoadingClick(view);
	}
});
```

More ways to use you can check it in my demo.



**Step 1.** Add the JitPack repository to your build file

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2.** Add the dependency

```
dependencies {
   compile 'com.github.wenqin-231:StatusView:v0.1'
}
```



### More

The default empty view and error view are from [dribbble](https://dribbble.com/shots/2326563-The-Expression-of-The-Fork).

![](https://github.com/wenqin-231/StatusView/blob/master/art/dirbbble-icon.png?raw=true)