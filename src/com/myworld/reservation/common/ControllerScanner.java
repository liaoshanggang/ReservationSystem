package com.myworld.reservation.common;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.myworld.reservation.annotation.Controller;
import com.myworld.reservation.annotation.RequestMapping;

/**
 * 将com.myworld.reservation.controller中所有标注了@Controller注解的类找到并加载
 * 如何让DispatcherServlet接收一个请求后通过解析/user/login字符串后，自动调用相应的方法，并组装数据呢？
 *思路：
 *是先得到com.lanqiao.store.controller包中的*.class文件，去掉.class后缀。再通过
 *Class<?> ctrlCls = ClassUtils.getClass(clsFullName, true);
 *得到控制器的Class实例(只标注了@Controller注解的类)。接着找到所有标注 @RequestMapping("/user/login") 的方法。
 *1. 找到package.controller包中所有标了@Controller注解的类,通过目录获得同级目录下的文件名
 *2. 找到该类中所有标注了@RequestMapping的方法
 *3. 将本控制器和方法封装到一个JavaBean（ControllerMetaData）中便于随后使用
 *4. 将方法上@RequestMapping注解的属性值作为Map的key,以ControllerMetaData对象作为value存储在Map中。
 * @author gg
 *
 */
public class ControllerScanner {
	private final static String CTRL_PKG_NAME = "com.myworld.reservation.controller."; 
	public static Map<String, ControllerMetaData> scan(){
		//将一个原本多线不安全的集合包装成一个线程安全的集合
		Map<String, ControllerMetaData> metadataMap  = Collections.synchronizedMap(new HashMap<String, ControllerMetaData>());
		try {
		//Class<?> class1 = Class.forName("com.myworld.reservation.controller.UserInfoController");
		//com.myworld.reservation.controller.UserInfoController
		//System.out.println(class1.getName());
		//file:/E:/%e8%93%9d%e6%a1%a5Java%e5%9f%b9%e8%ae%ad/20170812%e7%bd%91%e4%b8%8a%e8%ae%a2%e9%a4%90%e7%b3%bb%e7%bb%9f/ReservationSystem/build/classes/com/myworld/reservation/controller
		URL url = ControllerScanner.class.getResource("/com/myworld/reservation/controller");
		File dir = null;
		//1、通过路径获得控制器所在目录
		dir = new File(url.toURI());
		String[] ctrlFileNames  = dir.list();
		for (String ctrlName  : ctrlFileNames ) {
			//2、得到控制器的完整路径名：com.myworld.reservation.controller.UserController,拼接
			String ctrlFullName = CTRL_PKG_NAME + StringUtils.substringBefore(ctrlName, ".class");
			Class<?> ctrlClass = ClassUtils.getClass(ctrlFullName);
			//3、检查类是否存在@Controller注解
			if(ctrlClass.isAnnotationPresent(Controller.class)){
				//得到控制器类中所有声明了@RequestMapping注解的方法（private,public,...）
				Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(ctrlClass, RequestMapping.class);
				//控制器类中所有声明了@RequestMapping注解的方法
				for (Method method : methodsWithAnnotation) {
					System.out.println("method:"+method);
					ControllerMetaData controllerMetaData = new ControllerMetaData(ctrlClass, method);
					RequestMapping reMapping = method.getAnnotation(RequestMapping.class);
					///user/login
					String path  = reMapping.value();
					System.out.println("path:"+path);
					metadataMap.put(path, controllerMetaData);
				}
			}
		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return metadataMap;
	}
	public static void main(String[] args) {
		Map<String, ControllerMetaData> scan = scan();
		System.out.println(scan);
	}
}
