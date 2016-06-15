package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class VmBuilder {

    private int size;

    public Vm build() {
        return new Vm(size);
    }

    public VmBuilder ofSize(int size) {
        this.size = size;
        return this;
    }
}
