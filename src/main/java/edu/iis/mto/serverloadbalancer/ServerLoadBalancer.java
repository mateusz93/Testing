package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class ServerLoadBalancer {

    public void balance(Server[] servers, VirtualMachine[] virtualMachines) {
        if (virtualMachines.length > 0) {
            servers[0].currentLoadPercentage = 100.0d;
        }
    }
}
