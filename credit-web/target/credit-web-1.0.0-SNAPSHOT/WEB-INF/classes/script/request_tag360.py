#!/usr/bin/env python
# -*- encoding: utf-8 -*-
'''
Created on 2016年12月6日

@author: qinjl
'''

import urllib2

import struct
import random
import hashlib
import time
import json


import sys

reload(sys)
sys.setdefaultencoding('utf8')
'''
360黄页访问.
'''

class RequestBean(object):
    def __init__(self):
        self.appid = '1c35ba6bc1cc7c3a'
        self.timestamp =None
        self.v = '1.0'
        self.nonce = None
        self.secret = 'f34e5b1a55e3614de419f5b6fd04cd0b'

config = RequestBean()

def request_tag(phone_call_ask, dict_id_phone):
    params = {}
    list_key = ['appid', 'nonce', 'timestamp', 'v','sign']
    nonce = "%s"%hashlib.md5("%s"%random.randint(0,65535)).hexdigest()
    config.nonce = nonce[16:]
    config.timestamp = int(time.time()*1000)
    list_header_value = ['%s'%config.appid,'%s'%config.nonce,config.timestamp,'%s'%config.v]
    header = ""
    for value in list_header_value:
        header = header + "%s"%value
    str_phone_call = ""
    for map_field in phone_call_ask:
        list_call_keys = sorted(map_field.keys())
        for key in list_call_keys:
            str_phone_call = str_phone_call+"%s"%map_field[key]
    sign_str = header + str_phone_call + config.secret     
    sign_md5 = hashlib.md5(sign_str).hexdigest()
    sign = sign_md5[16:]   
    list_header_value.append(sign)
    data_header = dict(zip(list_key,list_header_value))
    params['header'] = data_header
    params['phone_call_ask'] = phone_call_ask
    params = json.dumps(params)
    try:
        result = request_method(params)
    except Exception,e:
        pass
        #print "请求错误:[%s]"%e
#     result.encode('UTF-8')
    if result is not None and len(result)>0:
        try:
            return paser_result(result,dict_id_phone)
        except Exception ,e:
            pass
            #print phone_call_ask
            #print "解析错误:[%s]"%e

def paser_result(result,dict_id_phone):
    json_string = json.loads(result)
    status = json_string['status']
    number_attr = json_string['number_attr']
    shopshow_dict = {}
    if status =='0':
        for fields in number_attr:
            if 'shop_show' in fields and fields['shop_show']:
                shop_show = fields['shop_show']
                phone = dict_id_phone['%s'%fields['id']]
                shop_show_name =shop_show['name']
                shopshow_dict[phone] = shop_show_name
            else:
                pass
    return shopshow_dict
    
def get_pack_head():
    header_size = 6
    b = 0x51
    c = random.randint(0,65535)
    d = 0
    str_pack = struct.pack('BBHH',header_size,b,c,d)
    return str_pack

def request_method(params):
    url = "http://openapi.shouji.360.cn/OpenapiPhoneQuery"
    str_pack = get_pack_head()
    data = str_pack + params
    try:
        request = urllib2.urlopen(url,data)
        result = request.read()
        return result
    except Exception,e:
        pass
        #print "请求错误:[%s]"%e
    return None

id_phone = 0
phone_call_ask = []
dict_id_phone = {}
for i in range(1, len(sys.argv)):
    phone = sys.argv[i].strip()
    dict_single ={'id':id_phone,'phone_num':phone}
    phone_call_ask.append(dict_single)
    dict_id_phone['%s'%id_phone] = phone
    id_phone += 1
    
if len(phone_call_ask) > 0:
    result = request_tag(phone_call_ask,dict_id_phone)
    if not result:
        print ""
    else:
        print json.dumps(result, ensure_ascii=False)
