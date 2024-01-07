## bundles assembler
先定义一下bundle和foundation,bundle是依附于app framework的native bundle(静态组件，动态插件)、flutter bundle、react native bundle、hybrid bundle，有些bundle具有动态性能被app framework动态加载；foundation是赋予上层能力的基础服务，更像是一些用来快速开发页面的toolkits，比如网络、存储、图像、音视频都是foundation。bundle之间存在通信，比如页面路由。

![architecture](./art/architecture.png)

### 配置module
bundle和foundation在gradle眼里都是module,所以一开始需要在module_config.json配置模块，模块配置好，还需要手动用android studio创建模块，这一块后面可以做成自动化生成。
```
  "allModules": [
    ...
    {
      "simpleName": "hotel-bundle1", #给idea plugin显示用
      "canonicalName": ":hotel-module:bundle1", #给settings.gradle include使用
      "format": "bundle",
      "group": "hotel",
      "binary_artifact": "com.jamesfchen.b:hotel-bundle1:1.0", #给project implementation使用
      "deps": [    #依赖项
        ":hotel-module:foundation"
      ]
    },
    {
      "simpleName": "hotel-bundle2",
      "canonicalName": ":hotel-module:bundle2",
      "format": "bundle",
      "group": "hotel",
      "binary_artifact": "com.jamesfchen.b:hotel-bundle2:1.0",
      "deps": [
        ":hotel-module:foundation"
      ]
    },
    ...
   ]
```
当配置好module，第一次需要运行指令`./gradlew publishAll`将所有的模块发布到maven仓库,目前只做到发布到mavenlocal后续需要发布到远程maven。


### 选择模块

local.properties
```
excludeModules=hotel-bundle2,
sourceModules=app,hotel-bundle1,\
    hotel-main,hotel-foundation,hotel-lint,\
  framework-loader,framework-router,framework-network
apps=hotel-main,app,home-main
```
~~在项目的local.properties配置不参加编译的项目(则不会被编译)，参加编译的项目会有两种形式一种binary(aar/jar)或者source code，后面提供idea plugin管理这些模块。第一次编译一定要源码编译所有文件，所以不能有exclude Modules，通过./gradlew publishAll发布所有模块到maven local 或者远程maven仓库。~~

利用工具(tools/module-manager-plugin-1.0.1.jar)来选择模块，对于fwk组必须不被exclude，因为作为基础服务要集成到项目中，exclude只会对app framework以上的模块，如果有兴趣了解工具的源码，来这里[康康](https://github.com/JamesfChen/bundles-assembler/tree/main/module-manager-intellij-plugin), 来点个![img](https://github.com/JamesfChen/bundles-assembler/blob/main/b/img.png)


![picture](https://github.com/JamesfChen/bundles-assembler/blob/main/b/tools/bundles.png)

### 组件通信(ibc,inter-bundle communication)

页面路由
- 利用android framework层的intent uri路由跳转
- 在app framework实现路由跳转，需要将app层的路由器发布到app framework的路由器管理中心，当需要跳转时，app framework会到管理中心find获取路由器，然后进行跳转

cbpc,cross bundle procedure call
- 暴露api给外部bundle模块，然后内部实现接口，需要在app framework注册暴露的api，方便search，实现方式与页面路由的第二种方法相似

### 监听App生命周期
使用lifecycle-plugin，自动注册监听App，使用方式，移步这个项目[spacecraft-android-gradle-plugin](https://github.com/JamesfChen/spacecraft-android-gradle-plugin)

项目结构
```
hotel-module
--- bundle1 bundle1
--- bundle2 bundle2
--- foundation 组件们的公共库
--- main 调试组件的入口
--- hotel-lint lint规则
framework
--- loader  framework的加载器
--- network 网络库
--- ibc  inter-bundle communication。页面路由（http路由，模块路由）、bundle rpc、message
--- common  公共代码
tools 项目工具
```

todo:
- [优化构建速度](https://developer.android.com/studio/build/optimize-your-build?hl=zh-cn)