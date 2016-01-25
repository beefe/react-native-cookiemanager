react-native cookie manager library.

- [x] iOS
- [x] Android

## 安装

```sh
$ npm install react-native-cookiemanager --save
```

## 配置

### IOS
- 打开你的Xcode项目, 在主目录调出快捷菜单点击`New Group`新建一个文件夹(比如`CookieManager`)
- 进入`node_modules`下的`react-native-cookiemanager`，拷贝`RCTCookieManager.h`和`RCTCookieManager.m`到创建的文件夹里面

### Android
- [Update Gradle Settings](#Update Gradle Settings)
- [Update App Gradle Build](#Update App Gradle Build)
- [Register React Package](#Register React Package)


##### Update Gradle Settings

```gradle
// file: android/settings.gradle
...

include ':cookiemanager', ':app' 
project(':cookiemanager').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-cookiemanager/android')
 // if there are more library
 // include ':app' , ':libraryone' , ':librarytwo' , 'more...'
 // project(':libraryonename').projectDir = new File(rootProject.projectDir, '../node_modules/libraryonemodule')
 // project(':librarytwoname').projectDir = new File(rootProject.projectDir, '../node_modules/librarytwomodule')
 // more..
```

##### Update App Gradle Build

```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':cookiemanager')
}
```

##### Register React Package
 
* after react-native@0.18
```java
...
import com.heng.cookie.CookieManagerPackage;

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new CookieManagerPackage()) // register cookie manager package
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "AwesomeProject", null);
        setContentView(mReactRootView);
    }
...

```

 * before react-native@0.18
```java
...
import com.heng.cookie.CookieManagerPackage;

public class MainActivity extends ReactActivity {

...

     @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new CookieManagerPackage());
    }

```


## 使用

```js

import CookieManager from 'react-native-cookiemanager';

var options = {
  name: '',
  value: '',
  domain: '',
  path: '',
  version: '',
  expiration: '',
};

CookieManager.set(options,(res) => {
  
});

CookieManager.getAll((res) => {
  
});

CookieManager.clearAll((res) => {
  
});
```
