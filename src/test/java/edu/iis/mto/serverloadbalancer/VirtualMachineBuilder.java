package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class VirtualMachineBuilder {

    private int size;

    public VirtualMachineBuilder ofSize(int size) {
        this.size = size;
        return this;
    }

    public VirtualMachine build() {
        return new VirtualMachine();
    }
}
