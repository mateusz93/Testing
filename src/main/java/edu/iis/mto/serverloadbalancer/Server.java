package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class Server {

    private double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms;
    private final double MAXIMUM_LOAD = 100.0d;

    public Server(int capacity) {
        this.capacity = capacity;
        vms = new ArrayList<Vm>();
    }

    public Server(int capacity, double loadedLevel) {
        this.capacity = capacity;
        this.currentLoadPercentage = loadedLevel;
        vms = new ArrayList<Vm>();
        addExpectedVmsCount(capacity);
    }

    private void addExpectedVmsCount(int capacity) {
        for (int i = 0; i < currentLoadPercentage / capacity; ++i) {
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
        if (this.currentLoadPercentage + vm.size / (double) capacity * MAXIMUM_LOAD <= MAXIMUM_LOAD) {
            this.currentLoadPercentage += vm.size / (double) capacity * MAXIMUM_LOAD;
            vms.add(vm);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int vmsCount() {
        return vms.size();
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }
}
