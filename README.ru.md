react-native cookie manager library. [![npm version](https://img.shields.io/npm/v/react-native-cookiemanager.svg?style=flat-square)](https://www.npmjs.com/package/react-native-cookiemanager)

- [x] iOS
- [x] Android

## Installation / 安装

```sh
$ npm install react-native-cookiemanager --save
```

## Configuration / 配置

### IOS
- Откройте свой проект Xcode, вызовите контекстное меню, выберите команду `New Group`, чтобы создать новую папку (например, `CookieManager`) в вашем домашнем каталоге
- Скопируйте файы из `react-native-cookiemanager` RCTCookieManager.h и RCTCookieManager.m 

### Android
- [Update Gradle Settings](#update-gradle-settings)
- [Update App Gradle Build](#update-app-gradle-build)
- [Register React Package](#register-react-package)


##### Update Gradle Settings

```gradle
// file: android/settings.gradle
...

include ':react-native-cookiemanager'
project(':react-native-cookiemanager').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-cookiemanager/android')

```

##### Update App Gradle Build

```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':react-native-cookiemanager')
}
```

##### Register React Package
 
* before RN v0.18
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

 * RN v0.18 - RN v0.29.0
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

* after RN v0.29.0
```java
...
import com.heng.cookie.CookieManagerPackage;
...

public class MainApplication extends Application implements ReactApplication {


    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        protected boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new CookieManagerPackage()
            );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}

```


## Use / 使用

```js

import CookieManager from 'react-native-cookiemanager';

let options = {
  name: '',
  value: '',
  domain: '',
  origin: '',
  path: '',
  expiration: '',
};

CookieManager.setCookie(options);

CookieManager.getCookie('you url',(res) => {
  alert(JSON.stringify(res));
});

CookieManager.removeAllCookies();
```
