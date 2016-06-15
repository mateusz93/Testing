package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class VmBuilder implements IBuilder<Vm> {

    private int size;

    public Vm build() {
        return new Vm(size);
    }

    public VmBuilder sizeOf(int size) {
        this.size = size;
        return this;
    }
}
