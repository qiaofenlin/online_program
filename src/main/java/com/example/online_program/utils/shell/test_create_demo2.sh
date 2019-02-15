#!/usr/bin/env bash

USER_COUNT=`cat /etc/passwd | grep '^zhangsan:' -c`
USER_NAME='zhangsan'
if [ $USER_COUNT -ne 1 ]
 then
 useradd $USER_NAME
 echo "123456" | passwd $USER_NAME --stdin
 else
 echo 'user exits'
fi