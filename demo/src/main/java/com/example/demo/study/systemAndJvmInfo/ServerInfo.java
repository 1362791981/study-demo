package com.example.demo.study.systemAndJvmInfo;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 获取系统cpu、内存、磁盘 | JVM堆、内存等信息
 * @ClassName: ServerInfo
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-25 09:28
 */
@Slf4j
public class ServerInfo {

    public static void main(String[] args) {
        while (true) {
            try {
                getServer();
                printlnCpuInfo();
                MemInfo();
                getThread();
                setSysInfo();
                setJvmInfo();
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void getServer() {
        //获取jvm的堆内存代码
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        log.info("jvm.heap.init is " + (heapMemoryUsage.getInit()) + "KB  " + formatByte(heapMemoryUsage.getInit()));
        log.info("jvm.heap.used is " + (heapMemoryUsage.getUsed()) + "KB  " + formatByte(heapMemoryUsage.getUsed()));
        log.info("jvm.heap.committed is " + (heapMemoryUsage.getCommitted()) + "KB  " + formatByte(heapMemoryUsage.getCommitted()));
        log.info("jvm.heap.max is " + (heapMemoryUsage.getMax()) + "KB  " + formatByte(heapMemoryUsage.getMax()));

        //获得jvm的非堆内存代码
        MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        log.info("jvm.nonheap.init is " + (nonHeapMemoryUsage.getInit()) + "KB  " + formatByte(nonHeapMemoryUsage.getInit()));
        log.info("jvm.nonheap.used is " + (nonHeapMemoryUsage.getUsed()) + "KB  " + formatByte(nonHeapMemoryUsage.getUsed()));
        log.info("jvm.nonheap.committed is " + (nonHeapMemoryUsage.getCommitted()) + "KB  " + formatByte(nonHeapMemoryUsage.getCommitted()));
        log.info("jvm.nonheap.max is " + (nonHeapMemoryUsage.getMax()) + "KB  " + formatByte(nonHeapMemoryUsage.getMax()));

        //堆和非堆里面都几个不同的区(各种区的具体参数)
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            final String kind = pool.getType() == MemoryType.HEAP ? "heap" : "nonheap";
            final MemoryUsage usage = pool.getUsage();
            log.info("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName() + ".init is " + usage.getInit() + "KB  " + formatByte(usage.getInit()));
            log.info("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName() + ".used is " + usage.getUsed() + "KB  " + formatByte(usage.getUsed()));
            log.info("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName() + ".committed is " + usage.getCommitted() + "KB  " + formatByte(usage.getCommitted()));
            log.info("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName() + ".max is " + usage.getMax() + "KB  " + formatByte(usage.getMax()));
        }


    }

    private static void printlnCpuInfo() throws InterruptedException {
        //log.info("----------------cpu信息----------------");
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        TimeUnit.SECONDS.sleep(1);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        log.info("----------------cpu信息----------------");
        log.info("cpu核数:" + processor.getLogicalProcessorCount());
        log.info("cpu系统使用率:" + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        log.info("cpu用户使用率:" + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        log.info("cpu当前等待率:" + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        log.info("cpu当前使用率:" + new DecimalFormat("#.##%").format(1.0 - (idle * 1.0 / totalCpu)));
    }

    public static void MemInfo() {
        log.info("----------------主机内存信息----------------");
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        //总内存
        long totalByte = memory.getTotal();
        //剩余
        long acaliableByte = memory.getAvailable();
        log.info("总内存 = " + formatByte(totalByte));
        log.info("使用" + formatByte(totalByte - acaliableByte));
        log.info("剩余内存 = " + formatByte(acaliableByte));
        log.info("使用率：" + new DecimalFormat("#.##%").format((totalByte - acaliableByte) * 1.0 / totalByte));
    }

    public static void setSysInfo() {
        log.info("----------------操作系统信息----------------");
        Properties props = System.getProperties();
        //系统名称
        String osName = props.getProperty("os.name");
        //架构名称
        String osArch = props.getProperty("os.arch");
        log.info("操作系统名 = " + osName);
        log.info("系统架构 = " + osArch);
    }

    public static void setJvmInfo() {
        log.info("----------------jvm信息----------------");
        Properties props = System.getProperties();
        Runtime runtime = Runtime.getRuntime();
        //jvm总内存
        long jvmTotalMemoryByte = runtime.totalMemory();
        //jvm最大可申请
        long jvmMaxMoryByte = runtime.maxMemory();
        //空闲空间
        long freeMemoryByte = runtime.freeMemory();
        //jdk版本
        String jdkVersion = props.getProperty("java.version");
        //jdk路径
        String jdkHome = props.getProperty("java.home");
        log.info("jvm内存总量 = " + formatByte(jvmTotalMemoryByte));
        log.info("jvm已使用内存 = " + formatByte(jvmTotalMemoryByte - freeMemoryByte));
        log.info("jvm剩余内存 = " + formatByte(freeMemoryByte));
        log.info("jvm内存使用率 = " + new DecimalFormat("#.##%").format((jvmTotalMemoryByte - freeMemoryByte) * 1.0 / jvmTotalMemoryByte));
        log.info("java版本 = " + jdkVersion);
        //log.info("jdkHome = " + jdkHome);
    }

    public static void getThread() {
        log.info("----------------线程信息----------------");
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

        while (currentGroup.getParent() != null) {
            // 返回此线程组的父线程组
            currentGroup = currentGroup.getParent();
        }
        //此线程组中活动线程的估计数
        int noThreads = currentGroup.activeCount();

        Thread[] lstThreads = new Thread[noThreads];
        //把对此线程组中的所有活动子组的引用复制到指定数组中。
        currentGroup.enumerate(lstThreads);
        for (Thread thread : lstThreads) {
            log.info("线程数量：" + noThreads + " 线程id：" + thread.getId() + " 线程名称：" + thread.getName() + " 线程状态：" + thread.getState());
        }
    }

    public static String formatByte(long byteNumber) {
        //换算单位
        double FORMAT = 1024.0;
        double kbNumber = byteNumber / FORMAT;
//        if (kbNumber < FORMAT) {
//            return new DecimalFormat("#.##KB").format(kbNumber);
//        }
        double mbNumber = kbNumber / FORMAT;
        if (mbNumber < FORMAT) {
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber / FORMAT;
        if (gbNumber < FORMAT) {
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber / FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }

}
