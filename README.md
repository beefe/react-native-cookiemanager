react-native cookie manager library.

- [x] iOS
- [x] Android

## 安装

```sh
$ npm install react-native-cookiemanager --save
```

## 配置

### [Android]('./android/README.md')

### IOS
- 打开你的Xcode项目, 在主目录调出快捷菜单点击`New Group`新建一个文件夹(比如`CookieManager`)
- 进入`node_modules`下的`react-native-cookiemanager`，拷贝`RCTCookieManager.h`和`RCTCookieManager.m`到创建的文件夹里面

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
