## MySQL 主从复制

### 🗂️ 项目结构

```txt
├─mysql-master
│  └─volumes
│      ├─conf
│      │  └─my.cnf
│      ├─data
│      └─log
├─mysql-slave
│  └─volumes
│      ├─conf
│      │  └─my.cnf
│      ├─data
│      └─log
└─docker-compose.yml
```

### 🚀 启动容器

```sh
docker-compose up -d
```

### 🧩 主服务器配置

1. 进入主服务器容器

```sh
docker exec -it mysql-master bash
```

2. 进入 MySQL 客户端

```bash
mysql -uroot -p123456
```

3. 创建复制用户，并授予权限，用于同步数据

```sql
CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
GRANT REPLICATION SLAVE ON *.* TO 'slave'@'%';
FLUSH PRIVILEGES;
```

4. 记录主服务器的二进制日志文件名和位置（即File和Position对应的值）

```sql
SHOW MASTER STATUS;
```

### 🔁 从服务器配置

1. 查询主服务器IP

```sh
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${MYSQL_MASTER_NAME}
```

2. 进入从服务器容器

```sh
docker exec -it ${MYSQL_SLAVE_NAME} bash
```

3. 进入 MySQL 客户端

```bash
mysql -uroot -p123456
```

4. 配置从服务器连接主服务器

```sql
CHANGE MASTER TO 
master_host='${MYSQL_MASTER_HOST}',
master_port=3306,
master_user='slave',
master_password ='123456',
master_log_file='${MYSQL_MASTER_LOG_FILE}',
master_log_pos=${MYSQL_MASTER_LOG_POS},
master_connect_retry=60;
```

---

- master_host: 主服务器的IP地址
- master_port: 主服务器的端口号，不是映射外部宿主主机的端口号
- master_user: 主服务器的复制用户，即授权用于数据同步的用户
- master_password: 主服务器的复制用户密码
- master_log_file: 主服务器的二进制日志文件名，指定在Slave从哪个日志文件开始复制
- master_log_pos: 主服务器的二进制日志文件位置，指定在Slave从哪个位置开始复制
- master_connect_retry: 连接主服务器失败后，重试的时间间隔，单位秒，默认60秒

5. 启动从服务器

```sql
START SLAVE;
```

6. 查看从服务器状态

```sql
SHOW SLAVE STATUS \G;
```

---

- Slave_IO_Running: Yes 表示从服务器的IO线程正在运行
- Slave_SQL_Running: Yes 表示从服务器的SQL线程正在运行

### ✅ 测试验证

用 Mysql Workbench 连接主从服务器，查看数据是否同步。

```sql
CREATE DATABASE test;
USE test;
CREATE TABLE test (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50));
INSERT INTO test (name) VALUES ('test1');
```

### 📚 参考资料

- [基于 Docker-Compose 搭建 MySQL 主从复制](https://www.cnblogs.com/haima/p/14341903.html)
- [MySQL 主从复制详解（原理 + 实践）](https://www.cnblogs.com/wzh2010/p/15049805.html)