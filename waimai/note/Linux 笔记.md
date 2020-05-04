# Linux 笔记

## 01 wget命令

例：显示百度返回内容

wget -q -O - https://www.baidu.com

-q,--quiet 不显示输出信息；

 -O,--output-file=FILE 将软件输出信息保存到文件；

例：下载192.168.1.168的首页并将下载过程中的的输入信息保存到 test.htm文件中

wget -O test.htm [http://192.168.1.168](http://192.168.1.168/)

