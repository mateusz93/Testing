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

    public Server(int capacity, double loadedLevel) {
        this.capacity = capacity;
        this.currentLoadPercentage = loadedLevel;
        vms = new ArrayList<Vm>();
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
        if (this.currentLoadPercentage + vm.size / (double) capacity * 100.0d <= 100.0d) {
            this.currentLoadPercentage += vm.size / (double) capacity * 100.0d;
            vms.add(vm);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int vmsCount() {
        return vms.size();
    }
}
