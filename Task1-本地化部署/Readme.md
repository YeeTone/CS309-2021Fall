# JCoder Online Judge系统本地化部署记录

操作系统：Windows 10

## 前端部署

## JCoder Docker容器部署

报错：Hardware assisted virtualization and data execution protection must be enabled in the BIOS

### 1：在计算机上没有Hyper-V    

解决方案：
- 复制以下内容进入记事本中，并重命名为Hyper-V.cmd文件
```
pushd "%~dp0"

dir /b %SystemRoot%\servicing\Packages\*Hyper-V*.mum >hyper-v.txt

for /f %%i in ('findstr /i . hyper-v.txt 2^>nul') do dism /online /norestart /add-package:"%SystemRoot%\servicing\Packages\%%i"

del hyper-v.txt

Dism /online /enable-feature /featurename:Microsoft-Hyper-V-All /LimitAccess /ALL
```

保存后使用管理员权限运行该cmd文件

运行完成后按```Y```重启计算机。

### 2: Hyper-V管理器中没有DockerNAT

解决方案：     

重启Hyper-V，使用以下命令：        
```
net stop vmms
net start vmms
```

或者是以下命令：
```
bcdedit /set hypervisorlaunchtype off 
bcdedit /set hypervisorlaunchtype auto
```

运行了两条命令后需要重启计算机。
