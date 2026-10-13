# 演唱会票务系统

基于 Spring Boot + Vue 前后端分离的演唱会票务管理系统。包含前台购票、后台管理、在线客服、新闻公告、订单充值等完整业务模块。

## 技术栈

### 后端
- Spring Boot 2.2.2
- MyBatis-Plus（持久层）
- Apache Shiro（鉴权）
- MySQL 8.x
- FastJSON、Hutool、Apache POI、HttpClient

### 前端
- Vue 2.6 + Vue Router + Vuex
- Element UI（组件库）
- ECharts（图表）、Swiper（轮播）、vue-amap（地图）
- 两套前端：**admin**（后台管理）+ **front**（用户前台）

## 功能模块

| 模块 | 说明 |
| --- | --- |
| 用户管理 | 用户注册、登录、个人信息维护 |
| 票务信息 | 演唱会票务信息展示、分类筛选 |
| 票务类型 | 票务分类管理 |
| 订单管理 | 下单、订单查询、订单状态流转 |
| 充值记录 | 用户账户充值流水 |
| 在线客服 | 用户与工作人员实时聊天 |
| 留言反馈 | 用户留言、管理员回复 |
| 新闻公告 | 演唱会资讯发布与浏览 |
| 收藏功能 | 用户收藏感兴趣的票务 |
| 系统介绍 | 关于系统介绍页 |
| 工作人员 | 客服/工作人员账号管理 |
| 系统配置 | 轮播图、系统参数等配置 |
| 文件上传 | 通用文件/图片上传接口 |

## 项目结构

```
ConcertTicketSystem/
├── src/main/java/com/             # 后端代码
│   ├── controller/                # 控制器（16 个业务模块）
│   ├── service/                   # 业务层
│   ├── dao/                      # 数据访问层
│   ├── entity/                    # 实体类
│   └── ...
├── src/main/resources/
│   ├── application.yml            # 配置文件
│   ├── mapper/                    # MyBatis SQL 映射
│   ├── admin/admin/               # 后台管理前端（Vue）
│   └── front/front/               # 用户前台前端（Vue）
├── db/
│   └── concertticketsystem.sql    # 数据库脚本（含建库语句 + 数据）
└── pom.xml                        # Maven 依赖
```

## 快速开始

### 环境要求
- JDK 1.8
- Maven 3.5+
- MySQL 8.x
- Node.js 10+ / npm

### 1. 导入数据库

```bash
mysql -u root -p < db/concertticketsystem.sql
```
SQL 文件开头已包含 `CREATE DATABASE` 语句，会自动创建 `springbooth56pqtxo` 数据库。

### 2. 配置数据库连接

编辑 `src/main/resources/application.yml`，将 `${DB_PASSWORD:root}` 中的默认值改成你本地 MySQL 的密码，或者设置环境变量：

```bash
# Windows PowerShell
$env:DB_PASSWORD="你的MySQL密码"

# Linux/Mac
export DB_PASSWORD="你的MySQL密码"
```

### 3. 启动后端

```bash
mvn spring-boot:run
```

启动成功后访问：http://localhost:8080/springbooth56pqtxo

### 4. 启动前端

后台管理（admin）和用户前台（front）分别启动：

```bash
# 启动后台管理前端
cd src/main/resources/admin/admin
npm install
npm run serve

# 启动用户前台前端
cd src/main/resources/front/front
npm install
npm run serve
```

启动后按命令行提示的端口访问（通常为 8081 / 8082）。

## 默认账号

| 角色 | 账号 | 密码 |
| --- | --- | --- |
| 系统管理员 | admin | admin |
| 工作人员 | 工号1 | 123456 |
| 普通用户 | 用户名1 | 123456 |

## 浏览器访问

- 后台管理：http://localhost:8080/springbooth56pqtxo/admin/dist/index.html
- 用户前台：根据前端启动端口访问

## 部署说明

生产环境部署时：
- 后端打包：`mvn clean package`，生成 jar 后用 `java -jar` 运行
- 前端打包：`npm run build`，将 `dist` 目录放到 `src/main/resources/static` 下
- 数据库密码请通过环境变量 `DB_PASSWORD` 注入，不要硬编码到配置文件
