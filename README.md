# kevinProject
本项目丢一些日常代码💾，总体以Java特性的学习为主，配菜有总结的Java编程模板、Java实现的《设计模式之禅》中的设计模式、Netty的入门demo。🙃



# 泛型







# Stream流

代码于[Java8StreamAPI.java](https://github.com/ChengYongchao/kevinProject/blob/master/src/main/java/com/kevin/stream/Java8StreamAPI.java)，看代码食用更香🍋

- 各个容器类实现Stream的方式:
	 - 所有的继承于**Collection**的容器类都可以通过调用Collection于1.8实现的stream方法
	 - **Map** 通过调用Map的entrySet()方法，获取Set再调用stream()
	 - **数组**可以调用Arrays.stream(T []array)实现流，同时还有参数不同的stream函数用来截取数组
	  - 直接将**值**转变成流对象
	  - I/O获取流
	  - 生成器获取流
- Stream流的常用方法
- 普通流到数值流的转换
- 对Map进行排序
- Stream收集器，优雅的结束stream



# 函数式接口



# 多线程



# 编程模板总结



# 设计模式



# Leetcode 



