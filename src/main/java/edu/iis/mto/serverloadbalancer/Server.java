package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class Server {

    public double currentLoadPercentage;
    public int capacity;
    private List<Vm> vms;
    private final double MAXIMUM_LOAD = 100.0d;

    public Server(int capacity) {
        this.capacity = capacity;
        vms = new ArrayList<Vm>();
    }

    public Server(int capacity, double loadedPercentage) {
        this.capacity = capacity;
        this.currentLoadPercentage = loadedPercentage;
        vms = new ArrayList<Vm>();
        for (int i = 0; i < loadedPercentage / capacity; ++i) {
            Vm vm = new Vm(1);
            vms.add(vm);
        }
    }

    public boolean contains(Vm vm) {
        for (Vm v : vms) {
            if (v == vm) {
                return true;
            }
        }
        return false;
    }

    public void addVm(Vm vm) {
        if (isFreeCapacity(vm)) {
            vms.add(vm);
            this.currentLoadPercentage += vm.size / (double)this.capacity * MAXIMUM_LOAD;
        }
    }

    public int getVmsCount() {
        return vms.size();
    }

    public boolean isFreeCapacity(Vm vm) {
        return (this.currentLoadPercentage + vm.size / (double)this.capacity * MAXIMUM_LOAD) <= MAXIMUM_LOAD;
    }
}
