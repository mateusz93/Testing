package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerLoadBalancer {

    private final double MAXIMUM_LOADED = 100.0d;

    public void balance(Server[] servers, Vm[] vms) {
        if (isNotEmpty(vms)) {
            servers[0].currentLoadPercentage = vms[0].size / (double)servers[0].capacity * MAXIMUM_LOADED;
        }
    }

    private boolean isNotEmpty(Vm[] vms) {
        return vms.length > 0;
    }
}
