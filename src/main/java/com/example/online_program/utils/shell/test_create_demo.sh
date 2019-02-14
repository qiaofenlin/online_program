#!/usr/bin/env bash


useradd  -d  /home/tom -m -s /bin/bash tom
sudo passwd tom #会让你输入新密码，你也可以加个sudo
#sudo passwd tom
#usermod -s /bin/bash tom #使bash 解析

# *************************
#useradd 123 -p 321 -m /home/var
#添加用户123，登陆密码为321 并强制指定该123用户的家目录为/home/var
