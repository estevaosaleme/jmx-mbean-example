package com.example;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class DemoJmx implements DemoJmxMBean {

    private int volume = 0;

    @Override
    public void increaseVolume() {
        volume++;
    }

    @Override
    public void decreaseVolume() {
        volume--;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    public static void main(String[] args) throws IOException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        DemoJmx demoService = new DemoJmx();
        ObjectName name;
        try {
            name = new ObjectName("com.example.DemoJmx:type=DemoJmxMBean,name=DemoJmxMetrics");
            mbs.registerMBean(demoService, name);
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException e) {
            e.printStackTrace();
        }
        System.out.println("Press ENTER to stop the application...");
        System.in.read();
    }
}
