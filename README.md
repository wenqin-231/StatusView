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

[Download Demo](http://fir.im/StatusView)

* It is the different status of Dialog, Error and Empty.

![](https://github.com/wenqin-231/StatusView/blob/master/art/dialog_loading.gif?raw=true)![](https://github.com/wenqin-231/StatusView/blob/master/art/error_loading.gif?raw=true)![](https://github.com/wenqin-231/StatusView/blob/master/art/empty_loading.gif?raw=true)

### Usage

* Use it  with `BaseStatusActivity` or `BaseStatusFragment` :

```java
public class LoadingActivity extends BaseStatusActivity {
  	// use it just by two line of code
  	// when you want to load something 
  	mStatusView.setStatus(Status.LOADING);
	// after loading finish 
  	mStatusView.setStatus(Status.Normal);
  	
 	// add a DefaultToolbar by default and you can setTitle or setCenterTitle for it.
  	setTitle("Loading in Activity");
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

* Besides, you can set the code in your BaseActivity or BaseFragment by copying the code from `BaseStatusActivity` or `BaseStatusFragment`：

```java
// init StatusView with default ToolBar in BasseFragment
@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	super.onViewCreated(view, savedInstanceState);
	StatusManager statusManager = StatusManager
		// Should be this and not set getActivity in it.
		.get(this)
		// Set your ContentView and it is usually the view that created in OnCreateView().
		.setContentView(view)
		// Set true to add a StatusView in your view and set false to avoid loading the unnecessary setting.
		.isAddStatusView(true)
		// Set true to add a Toolbar and set false the setToolbar() will be invalid.
		.isAddToolbar(true)
		// There will create a DefaultToolbar if you set null in it.
		.setToolbar(onCreateToolbar())
		.launch();

		mStatusView = statusManager.getStatusView();
		mToolbar = statusManager.getToolbar();
}
```

The code is so simple and you can set your custom attributes by using ` StatusManager`.

More ways of usage can be found in my demo.



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
   compile 'com.github.wenqin-231:StatusView:v0.35'
}
```



### More

The default empty view and error view are from [dribbble](https://dribbble.com/shots/2326563-The-Expression-of-The-Fork).

![](https://github.com/wenqin-231/StatusView/blob/master/art/dirbbble-icon.png?raw=true)

BTW, I write a [blog](https://medium.com/@wenqin231/%E4%B8%93%E6%B3%A8%E4%BA%8E%E6%98%BE%E7%A4%BA%E9%80%9A%E7%94%A8%E5%B8%83%E5%B1%80%E7%9A%84statusview-51e3ace6bb07) in Chinese about it.

At last , if you have an idea or others,  you can contact with me by my Email : wenqin231@gmail.com .

Hope you will like it.

### LICENSE

```
Copyright (c) 2017 wenqin-231

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```