# StatusView
This is a library to help you show the different status of view such as loading , empty and error easily.

### Features

* Support both in Activity and Fragment.
* Support the custom in different status of view.
* Support error view to retry request by default button.
* Easily use. 
* With a delay loading avoid the too fast loading.
* Support the Toolbar、NavigationBottomBar and etc.
* Can use it with my forked SpringView gracefully.

### Demo



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



