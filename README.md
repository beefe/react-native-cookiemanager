# cookie

## Installation and How to use

#### Step 1 - NPM Install

```shell
npm install --save react-native-cookie
```
#### Step 2 - Update Gradle Settings

```gradle
// file: android/settings.gradle
...

include ':reactcookie', ':app' 
project(':reactcookie').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-cookie')
 // if there are more library
 // include ':app' , ':libraryone' , ':librarytwo' , 'more...'
 // project(':libraryonename').projectDir = new File(rootProject.projectDir, '../node_modules/libraryonemodule')
 // project(':librarytwoname').projectDir = new File(rootProject.projectDir, '../node_modules/librarytwomodule')
 // more..
```

#### Step 3 - Update app Gradle Build

```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':reactcookie')
}
```

#### Step 4 - Register React Package

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
                .addPackage(new CookiePackage()) // register cookie package
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "AwesomeProject", null);
        setContentView(mReactRootView);
    }
...

```

#### Step 5 - Require and use in Javascript

```js
// file: index.android.js

var Cookie = require('react-native-cookie');

var options = {
  name: '',
  value: '',
  domain: '',
  path: '',
  version: '',
  expiration: '',
};

Cookie.set(options,(res) => {
  
});

```
