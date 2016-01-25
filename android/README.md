#### Update Gradle Settings

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

#### Update App Gradle Build

```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':cookiemanager')
}
```

#### Register React Package
 
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
