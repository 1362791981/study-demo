package com.example.demo.study.dubbo.provider;


/**
 * @Description: 生产者
 * @ClassName: Provider
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-22 09:57
 */
public class Provider {

//    public static void export(Object service, int port) throws Exception {
//        ServerSocket server = new ServerSocket(port);
//        while(true) {
//            Socket socket = server.accept();
//            new Thread(new Thread() {
//                //反序列化
//                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//                String methodName = input.read(); //读取方法名
//                Class<?>[] parameterTypes = (Class<?>[]) input.readObject(); //参数类型
//                Object[] arguments = (Object[]) input.readObject(); //参数
//                Method method = service.getClass().getMethod(methodName, parameterTypes);  //找到方法
//                Object result = method.invoke(service, arguments); //调用方法
//                // 返回结果
//                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
//                  output.writeObject(result);
//            }).start();
//        }
//    }
}
