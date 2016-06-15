package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        if (vms.length > 0 && servers.length == 1) {
            servers[0].addVm(vms[0]);
        }
    }
}
