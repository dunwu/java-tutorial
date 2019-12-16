package io.github.dunwu.javalib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019/10/30
 */
public class SystemInfoUtil {

    private static Logger logger = LoggerFactory.getLogger(SystemInfoUtil.class);

    public static void main(String[] args) {

        logger.info("Initializing System...");
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        logger.info("Checking computer system...");
        printComputerSystem(hal.getComputerSystem());
        logger.info("Checking Processor...");
        printProcessor(hal.getProcessor());
        logger.info("Checking Memory...");
        printMemory(hal.getMemory());
        logger.info("Checking CPU...");
        printCpu(hal.getProcessor());
        logger.info("Checking Sensors...");
        printSensors(hal.getSensors());
        logger.info("Checking Power sources...");
        printPowerSources(hal.getPowerSources());
        logger.info("Checking Disks...");
        printDisks(hal.getDiskStores());
        logger.info("Checking Network interfaces...");
        printNetworkInterfaces(hal.getNetworkIFs());
        // hardware: displays
        logger.info("Checking Displays...");
        printDisplays(hal.getDisplays());
        // hardware: USB devices
        logger.info("Checking USB Devices...");
        printUsbDevices(hal.getUsbDevices(true));
        OperatingSystem os = systemInfo.getOperatingSystem();
        System.out.println(os);
        logger.info("Checking Processes...");
        printProcesses(os, hal.getMemory());
        logger.info("Checking File System...");
        printFileSystem(os.getFileSystem());
        logger.info("Checking Network parameterss...");
        printNetworkParameters(os.getNetworkParams());
    }

    public static void printComputerSystem(final ComputerSystem computerSystem) {
        System.out.println("manufacturer: " + computerSystem.getManufacturer());
        System.out.println("model: " + computerSystem.getModel());
        System.out.println("serialnumber: " + computerSystem.getSerialNumber());
        final Firmware firmware = computerSystem.getFirmware();
        System.out.println("firmware:");
        System.out.println("  manufacturer: " + firmware.getManufacturer());
        System.out.println("  name: " + firmware.getName());
        System.out.println("  description: " + firmware.getDescription());
        System.out.println("  version: " + firmware.getVersion());
        System.out.println("  release date: " + (firmware.getReleaseDate() == null ? "unknown"
            : firmware.getReleaseDate() == null ? "unknown" : firmware.getReleaseDate()));
        final Baseboard baseboard = computerSystem.getBaseboard();
        System.out.println("baseboard:");
        System.out.println("  manufacturer: " + baseboard.getManufacturer());
        System.out.println("  model: " + baseboard.getModel());
        System.out.println("  version: " + baseboard.getVersion());
        System.out.println("  serialnumber: " + baseboard.getSerialNumber());
    }

    public static void printProcessor(CentralProcessor processor) {
        System.out.println(processor);
        System.out.println(" " + processor.getPhysicalProcessorCount() + " physical CPU(s)");
        System.out.println(" " + processor.getLogicalProcessorCount() + " logical CPU(s)");
        System.out.println("Identifier: " + processor.getIdentifier());
        System.out.println("ProcessorID: " + processor.getProcessorID());
    }

    public static void printMemory(GlobalMemory memory) {
        System.out.println("以使用内存: " + FormatUtil.formatBytes(memory.getAvailable()) + "总共内存"
            + FormatUtil.formatBytes(memory.getTotal()));
    }

    public static void printCpu(CentralProcessor processor) {
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        System.out.println("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
        // Wait a second...
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        System.out.println("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
        long user =
            ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice =
            ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys =
            ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle =
            ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait =
            ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq =
            ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
            - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal =
            ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
        System.out.format(
            "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%%n",
            100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
            100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu);
        // System.out.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadTicks() * 100);
        // System.out.format("CPU load: %.1f%% (OS MXBean)%n", processor.getProcessorCpuLoadBetweenTicks() * 100);
        double[] loadAverage = processor.getSystemLoadAverage(3);
        System.out.println("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
            + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
            + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
        // per core CPU
        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
        double[] load = processor.getProcessorCpuLoadBetweenTicks(processor.getProcessorCpuLoadTicks());
        for (double avg : load) {
            procCpu.append(String.format(" %.1f%%", avg * 100));
        }
        System.out.println(procCpu.toString());
    }

    public static void printSensors(Sensors sensors) {
        System.out.println("Sensors:");
        System.out.format(" CPU Temperature: %.1f°C%n", sensors.getCpuTemperature());
        System.out.println(" Fan Speeds: " + Arrays.toString(sensors.getFanSpeeds()));
        System.out.format(" CPU Voltage: %.1fV%n", sensors.getCpuVoltage());
    }

    public static void printPowerSources(PowerSource[] powerSources) {
        StringBuilder sb = new StringBuilder("Power: ");
        if (powerSources.length == 0) {
            sb.append("Unknown");
        } else {
            double timeRemaining = powerSources[0].getTimeRemaining();
            if (timeRemaining < -1d) {
                sb.append("Charging");
            } else if (timeRemaining < 0d) {
                sb.append("Calculating time remaining");
            } else {
                sb.append(String.format("%d:%02d remaining", (int) (timeRemaining / 3600),
                    (int) (timeRemaining / 60) % 60));
            }
        }
        for (PowerSource pSource : powerSources) {
            sb.append(String.format("%n %s @ %.1f%%", pSource.getName(), pSource.getRemainingCapacity() * 100d));
        }
        System.out.println(sb.toString());
    }

    public static void printDisks(HWDiskStore[] diskStores) {
        System.out.println("Disks:");
        for (HWDiskStore disk : diskStores) {
            boolean readwrite = disk.getReads() > 0 || disk.getWrites() > 0;
            System.out.format(" %s: (model: %s - S/N: %s) size: %s, reads: %s (%s), writes: %s (%s), xfer: %s ms%n",
                disk.getName(), disk.getModel(), disk.getSerial(),
                disk.getSize() > 0 ? FormatUtil.formatBytesDecimal(disk.getSize()) : "?",
                readwrite ? disk.getReads() : "?", readwrite ? FormatUtil.formatBytes(disk.getReadBytes()) : "?",
                readwrite ? disk.getWrites() : "?", readwrite ? FormatUtil.formatBytes(disk.getWriteBytes()) : "?",
                readwrite ? disk.getTransferTime() : "?");
            HWPartition[] partitions = disk.getPartitions();
            if (partitions == null) {
                // TODO Remove when all OS's implemented
                continue;
            }
            for (HWPartition part : partitions) {
                System.out.format(" |-- %s: %s (%s) Maj:Min=%d:%d, size: %s%s%n", part.getIdentification(),
                    part.getName(), part.getType(), part.getMajor(), part.getMinor(),
                    FormatUtil.formatBytesDecimal(part.getSize()),
                    part.getMountPoint().isEmpty() ? "" : " @ " + part.getMountPoint());
            }
        }
    }

    public static void printNetworkInterfaces(NetworkIF[] networkIFs) {
        System.out.println("Network interfaces:");
        for (NetworkIF net : networkIFs) {
            System.out.format(" Name: %s (%s)%n", net.getName(), net.getDisplayName());
            System.out.format("   MAC Address: %s %n", net.getMacaddr());
            System.out.format("   MTU: %s, Speed: %s %n", net.getMTU(), FormatUtil.formatValue(net.getSpeed(), "bps"));
            System.out.format("   IPv4: %s %n", Arrays.toString(net.getIPv4addr()));
            System.out.format("   IPv6: %s %n", Arrays.toString(net.getIPv6addr()));
            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
                || net.getPacketsSent() > 0;
            System.out.format("   Traffic: received %s/%s%s; transmitted %s/%s%s %n",
                hasData ? net.getPacketsRecv() + " packets" : "?",
                hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?",
                hasData ? " (" + net.getInErrors() + " err)" : "",
                hasData ? net.getPacketsSent() + " packets" : "?",
                hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?",
                hasData ? " (" + net.getOutErrors() + " err)" : "");
        }
    }

    public static void printDisplays(Display[] displays) {
        System.out.println("Displays:");
        int i = 0;
        for (Display display : displays) {
            System.out.println(" Display " + i + ":");
            System.out.println(display.toString());
            i++;
        }
    }

    public static void printUsbDevices(UsbDevice[] usbDevices) {
        System.out.println("USB Devices:");
        for (UsbDevice usbDevice : usbDevices) {
            System.out.println(usbDevice.toString());
        }
    }

    public static void printProcesses(OperatingSystem os, GlobalMemory memory) {
        System.out.println("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
        // Sort by highest CPU
        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, OperatingSystem.ProcessSort.CPU));
        System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");
        for (int i = 0; i < procs.size() && i < 5; i++) {
            OSProcess p = procs.get(i);
            System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),
                100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
                FormatUtil.formatBytes(p.getResidentSetSize()), p.getName());
        }
    }

    public static void printFileSystem(FileSystem fileSystem) {
        System.out.println("File System:");
        System.out.format(" File Descriptors: %d/%d%n", fileSystem.getOpenFileDescriptors(),
            fileSystem.getMaxFileDescriptors());
        OSFileStore[] fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            System.out.format(
                " %s (%s) [%s] %s of %s free (%.1f%%) is %s "
                    + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
                    + " and is mounted at %s%n",
                fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
                FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                fs.getVolume(), fs.getLogicalVolume(), fs.getMount());
        }
    }

    public static void printNetworkParameters(NetworkParams networkParams) {
        System.out.println("Network parameters:");
        System.out.format(" Host name: %s%n", networkParams.getHostName());
        System.out.format(" Domain name: %s%n", networkParams.getDomainName());
        System.out.format(" DNS servers: %s%n", Arrays.toString(networkParams.getDnsServers()));
        System.out.format(" IPv4 Gateway: %s%n", networkParams.getIpv4DefaultGateway());
        System.out.format(" IPv6 Gateway: %s%n", networkParams.getIpv6DefaultGateway());
    }

}
