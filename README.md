# StatusView [![](https://jitpack.io/v/wenqin-231/StatusView.svg)](https://jitpack.io/#wenqin-231/StatusView)

This is a library to help you show the different status of view such as loading , empty and error easily.

### Features

* Support both in Activity and Fragment.
* Support the custom view of different status.
* Support the way to retry request by default button in error status.
* Easily use. 
* With a delay loading by default avoid the too fast loading.
* Support the Toolbar、NavigationBottomBar and etc.
* Can use it with [my forked SpringView]("https://github.com/wenqin-231/SpringView") gracefully.

### Demo

[DownLoad Demo](http://fir.im/StatusView)

* It is the different status of Dialog, Error and Empty.

![](https://github.com/wenqin-231/StatusView/blob/master/art/dialog_loading.gif?raw=true)![](https://github.com/wenqin-231/StatusView/blob/master/art/error_loading.gif?raw=true)![](https://github.com/wenqin-231/StatusView/blob/master/art/empty_loading.gif?raw=true)

### Usage

* Use it  with `BaseStatusActivity` or `BaseStatusFragment` :

```java
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

```java
// init StatusView in Activity 
mStatusView = StatusView.initInActivity(this);
// setup btn click in Error View
mStatusView.setOnRetryBtnClickListener(new OnRetryBtnClickListener() {
	@Override
	public void onClick(View view) {
		onNormalLoadingClick(view);
	}
});
```

* And also, you can set the code in your BaseActivity or BaseFragment by copying the code from `BaseStatusActivity` or `BaseStatusFragment`：

```java
// init StatusView with default ToolBar in BasseFragment
ViewGroup parentView = (ViewGroup) view.getParent();
View fragmentView = buildFragmentView(view);

StatusManager statusManager = StatusManager.get(getActivity())
        .setParentView(parentView)
        .setContentView(fragmentView)
        .setToolbar(onCreateToolbar())
        .isAddStatusView(isAddStatusView())
        .isAddToolBar(isAddToolBar())
        .launch();

mToolbar = statusManager.getToolbar();
mStatusView = statusManager.getStatusView();
```

The code is so simple and you can set your custom attributes by using ` StatusManager`.

More ways of usage you can find it in my demo.



### Add the StausView

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
   compile 'com.github.wenqin-231:StatusView:v0.3'
}
```



### More

The default empty view and error view are from [dribbble](https://dribbble.com/shots/2326563-The-Expression-of-The-Fork).

![](https://github.com/wenqin-231/StatusView/blob/master/art/dirbbble-icon.png?raw=true)



At Last , if you have an idea or others,  you can contact with me by my Email : wenqin231@gmail.com .

Hope you will like it.