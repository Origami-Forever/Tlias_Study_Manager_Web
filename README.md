# Tlias 学习管理系统
基于 SpringBoot + MyBatis + Vue + Nginx 的校园员工/学员管理系统，前后端分离架构，支持 Linux 服务器部署。

## 技术栈
### 后端
- SpringBoot 2.x
- MyBatis
- MySQL
- 全局异常处理、统一返回结果
- Knife4j 接口文档

### 前端
- Vue
- Nginx 反向代理部署

## 环境要求
- JDK 1.8+
- Maven 3.6+
- MySQL 5.7 / 8.0
- Nginx（服务器部署）

## 项目功能
- 员工/学员信息管理
- 部门管理
- 登录认证
- 分页查询、条件检索
- 新增、编辑、删除业务数据

## 快速启动
1. 导入 SQL 脚本到 MySQL
2. 修改 `application.yml` 数据库账号密码
3. Maven 刷新依赖
4. 启动 SpringBoot 主类
5. 访问后端接口：http://localhost:8080
6. 接口文档地址：http://localhost:8080/doc.html

## 服务器部署
1. 后端打包为 jar 包
2. 服务器安装 JDK、MySQL、Nginx
3. 导入数据库脚本
4. 启动 jar 包后台运行
5. Nginx 配置反向代理前端静态资源与后端接口

## 项目优化点
- 规范 Git 提交忽略文件
- 统一全局异常处理
- 统一接口返回格式
- 接入 Knife4j 在线接口文档
- 代码分层清晰，便于二次开发
