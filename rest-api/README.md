# rest-api 客户端
> [容医健康] 客户端消费者

# 领取礼包

```
post  http://m.ximalaya.com/open-activities/activityTrace/drawActivityItemByItemIdOutside

```

## 入参

```
{
  "appKey":"823e00c2814a405ba69e3d58ffba304b",
  "interfaceName":"menuList",
  "timestamp":1565233484057,
  "dataParams":"ODtF9ayYg93tipUa9T72lw==",
  "apiSign":"b7e54cf972f1ce68ec09ad24f1b90bac"
  "healthExamination": 1
}
```
###  说明

> appKey  接口调用秘钥  通过 com.etycx.rest.common.utils.UuidUtils 工具类中 main方法生成

> interfaceName  调用接口名称

> healthExamination  医院id

> timestamp  时间戳

> dataParams 数据加密入参  参看下文

> apiSign 数据参数签名  参看下文

### 参数签名生成方式

apiSign = md5(原始字符串)

原始字符串 组成 

appKey=823e00c2814a405ba69e3d58ffba304b#interfaceName=menuList#timestamp=timestamp#dataParams=

注意上面的顺序和等号左右不能有空格

## 返回值

```
{
   "code": -200000,
   "message": "权益不存在",
}
```

### 说明

> code 

code 为 0 表示成功 
code 小于0 具体错误查看 返回的 message 来判断

> message 
  







