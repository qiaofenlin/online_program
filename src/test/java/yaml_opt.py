#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 18-10-8 下午2:02
# @Author  : qfl
# @Site    : 
# @File    : yaml_opt.py
# @Software: PyCharm
# import json
# import os
# import time
#
# import ujson as ujson
# import yaml
# import sys
#
# # reload(sys)
# # sys.setdefaultencoding('utf8')
#
#
#
# # ------
# import yaml

# obj1 = {"redis":{"name": {"a":{"aa":"aaa"}}, "age": 20}}
# obj2 = {'a': [4,'111']}
# obj3 = {'b': [5,'6']}
#
# with open('test.yaml', 'w') as f:
#     yaml.dump_all([obj1, obj2,obj3], f)
# print( "*"*20)
#
# ys = yaml.load_all(file('test.yaml', 'r'))
#
# for y in ys:
#     print("aaa")
#     print y
#     # print(str(y.get('a')[0]))
# print( "*" * 20)
# import yaml
#
# y = yaml.dump(range(10))
# print y
#
# print("=====================1")
#
# # ## todo yaml取文件。
# ysss = yaml.load(open('root_conf.yaml', 'r'))
# test = "test"
# print(ysss['redis_callin'][test][0])
# print("####")
# #
# # ## todo yaml写文件
# # args = []
# # args.append({"test":ysss['redis_callin'][test]})
# # args.append({"pg":ysss['pg']["callin"]})
# #
# # with open('qfl.yaml', 'w') as f:
# #     yaml.dump_all(args, f)
#
#
# a ={"b1":"1","b":"2"}
# dict_temple = "'b':'{b}','b1':'{b1}'"
# dict_temple = dict_temple.format(**a)
# print("**********8")site
# print(dict_temple)


# site= {'name': '我的博客地址', 'alexa': 10000, 'url':'http://blog.csdn.net/uuihoo/'}
# del site['name'] # 删除键是'name'的条目
# # del site  # 清空字典所有条目
#
# print(site)


# site= "{'name': '我的博客地址', 'alexa': 10000, 'url':'http://blog.csdn.net/uuihoo/'}"
#
# site_dict = eval(site.decode('utf-8', 'ignore'))
# name = site_dict['name'].decode('utf-8', 'ignore')
# print(name)
#
# print(type(site_dict))
# print(site_dict)
# json = list()
# test = {
# 	  "number":"18322693235",
# 	  "name":"aaa",
# 	  "memo":"qflupload",
# 	  "info":{
# 	  }
# 	}
#
# for i in range(1,10000):
#     json.append(test)
#
# print(json)
# test = {'info': {}, 'memo': 'qfl upload', 'number': '18322693235', 'name': '\xe5\x88\x98\xe5\xb7\xa5'}
# testA = eval(test)
# print(testA)

#
# a = {'info': {}, 'memo': 'qfl upload', 'number': '18322693235', 'name': '111'}
# b = str(a)
# print(a)
# req_data = ujson.dumps(a)
# print ("========================")
# print (req_data)
# print (len(req_data))

#
# path = '/home/qiao/PycharmProjects/WorkNotes/md_files'
# os.makedirs(path)


# headers = ['number', 'name', 'memo', 'info']
#
# statement = 'create table {} (id serial PRIMARY KEY,\n call_status text,\n call_times integer,\n '.format("tabel_name")
# for i in range(len(headers)):
#     statement = (statement + '\n' + '{} {}' + ',').format(headers[i].lower(), 'text')
#     statement = statement[:-1] + ');CREATE INDEX {}_call_status_index ON public.{} (call_status);CREATE INDEX {}_number_index ON public.{} (number);'.format("tabel_name", "tabel_name", "tabel_name", "tabel_name")
# print(statement)

import time
now_time = time.strftime("%Y%m%d", time.localtime())
now_time_all = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
print(now_time_all)

print(now_time)
pro = "2018-12-04"
filt_result = pro.replace('-','')
print(filt_result)
print(now_time>=filt_result)

a = {"aaa":"123"}

# def add(a,b):
#     a = "2018-12-01 10:26:52"
#     print(a.split(' ')[0].replace('-',''))
#     print("*****************************************")
#     return a + b

