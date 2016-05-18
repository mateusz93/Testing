package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class Server {

    private static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;
    private List<VirtualMachine> virtualMachines = new ArrayList<VirtualMachine>();

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(VirtualMachine virtualMachine) {
        return virtualMachines.contains(virtualMachine);
    }

    public void addVirtualMachine(VirtualMachine vm) {
        currentLoadPercentage = vm.size / (double) capacity * MAXIMUM_LOAD;
        virtualMachines.add(vm);
    }

    public int countVms() {
        return virtualMachines.size();
    }
}
