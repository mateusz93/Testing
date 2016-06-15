package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class Server {

    public double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms;

    public Server(int capacity) {
        this.capacity = capacity;
        vms = new ArrayList<Vm>();
    }

    public boolean contains(Vm vm) {
        return true;
    }

    public void addVm(Vm vm) {
        this.currentLoadPercentage = vm.size / (double) capacity * 100.0d;
        vms.add(vm);
    }

    public int getCapacity() {
        return capacity;
    }

    public int vmsCount() {
        return vms.size();
    }
}
