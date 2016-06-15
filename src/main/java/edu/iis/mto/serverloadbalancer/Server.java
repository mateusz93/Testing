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

    public boolean contains(Vm vm) {
        for (Vm v : vms) {
            if (v == vm) {
                return true;
            }
        }
        return false;
    }

    public void addVm(Vm vm) {
        vms.add(vm);
        this.currentLoadPercentage = vm.size / (double)this.capacity * MAXIMUM_LOAD;
    }

    public int getVmsCount() {
        return vms.size();
    }

}
