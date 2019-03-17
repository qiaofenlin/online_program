#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 18-10-27 下午1:54
# @Author  : qfl
# @Site    :
# @File    : tts_xf.py
# @Software: PyCharm

#-*- coding: utf-8 -*-
import tornado.escape

data = {'appid': [b'1400098645'], 'text': [b'test_sign'], 'sign_type': [b'1'], 'remark': [b'test_sing_reamrk'], 'user_id': [b'n_ut_2019_02_19_92']}
args={}

# data = tornado.escape.native_str(data)
print("data {}".format(data))
for r in data:
    args[r] = tornado.escape.native_str(data[r][0])

for r in data:
    args[r] = tornado.escape.native_str(data[r][0])

print(args)
