package com.example.demo.study.dubbo.consumer;


/**
 * @Description: 消费者
 * @ClassName: Consumer
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-22 09:58
 */
public class Consumer {

//    public static <T> T refer (Class<T> interfaceClass, String host, int port) throws Exception {
//        return  (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass},
//                new InvocationHandler() {
//                    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
//                        Socket socket = new Socket(host, port);  //指定 provider 的 ip 和端口
//                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
//                        output.write(method.getName());  //传方法名
//                        output.writeObject(method.getParameterTypes());  //传参数类型
//                        output.writeObject(arguments);  //传参数值
//                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//                        Object result = input.readObject();  //读取结果
//                        return result;
//                    }
//                });
//    }
}
