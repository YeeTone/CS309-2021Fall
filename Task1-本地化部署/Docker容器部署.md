# JCoder Docker容器部署

## 指令集合
在dockerfile所在路径根据dockerfile创建docker镜像：
```
docker build -t docker-whale .
```

查看所有docker镜像：
```
docker images
```

运行docker镜像：
```
docker run docker-whale
```

使用docker镜像来启动docker容器：
```
docker run -t -i docker-whale /bin/bash
```

## 踩的坑

报错：Hardware assisted virtualization and data execution protection must be enabled in the BIOS

### 坑1：在计算机上没有Hyper-V    

解决方案：
- 复制以下内容进入记事本中，并重命名为Hyper-V.cmd文件
```
pushd "%~dp0"

dir /b %SystemRoot%\servicing\Packages\*Hyper-V*.mum >hyper-v.txt

for /f %%i in ('findstr /i . hyper-v.txt 2^>nul') do dism /online /norestart /add-package:"%SystemRoot%\servicing\Packages\%%i"

del hyper-v.txt

Dism /online /enable-feature /featurename:Microsoft-Hyper-V-All /LimitAccess /ALL
```

- 保存后使用管理员权限运行该cmd文件

- 运行完成后按```Y```重启计算机。

### 坑2: Hyper-V管理器中没有DockerNAT

解决方案：     

- 重启Hyper-V，使用以下命令：        
```
net stop vmms
net start vmms
```

或者是以下命令：
```
bcdedit /set hypervisorlaunchtype off 
bcdedit /set hypervisorlaunchtype auto
```

- 运行了两条命令后需要重启计算机。

### 坑3：使用dockerfile启动的镜像来启动容器时，容器显示ConnectionError的OSError，然后启动后很快就exit(1)异常退出

这种情况出现于尝试端口绑定的情况，如果使用指令集合的方法，则不会出现这种问题。

在dockerfile目录下尝试指令：
```
docker build -t nginx:v3 .
docker run -d -p 5000:5000 nginx:v3
```


