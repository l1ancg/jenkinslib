# jenkinslib

## 使用Library
- 创建jenkinslib仓库
- jenkins中配置全局lib
- 引入包：@Library('jenkinslib')
- 引用方法：def tools = new org.devops.tools()

## 使用在线Jenkinsfile
- 在流水线中使用SCM（Supply chain management）
- 配置仓库地址+指定分支+指定文件名

## 常用插件
- jdk parameter
- git parameter
- docker-build-step
- AnsiColor