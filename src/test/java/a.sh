#!/bin/sh
#转码
iconv -f gbk -t utf8 /usr/local/RPFiles/PC_YP_Export.bad  > /usr/local/RPFiles/transStr
lastVersion=/usr/local/RPFiles/transStr
#将转码后的数据赋予变量中 可以指定第几行数据如:'2p'则将文本中的第二行数据符给变量mydatestr
mydatestr=`sed -n 'p' $lastVersion`
echo "$mydatestr"
