package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
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
        currentLoadPercentage += loadOfVirtualMachine(vm);
        virtualMachines.add(vm);
    }

    public int countVms() {
        return virtualMachines.size();
    }

    public boolean canFit(VirtualMachine vm) {
        return currentLoadPercentage + loadOfVirtualMachine(vm) <= MAXIMUM_LOAD;
    }

    private double loadOfVirtualMachine(VirtualMachine vm) {
        return vm.size / (double) this.capacity * MAXIMUM_LOAD;
    }
}
