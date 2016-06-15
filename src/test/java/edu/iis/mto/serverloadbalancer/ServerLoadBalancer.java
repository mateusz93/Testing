package edu.iis.mto.serverloadbalancer;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerLoadBalancer {

    private double MAXIMUM_LOAD = 100.d;

    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm : vms) {
            getTheLeastLoadServer(servers).addVm(vm);
        }
    }

    private Server getTheLeastLoadServer(Server[] servers) {
        int theLeastLoadedServerIndex = -1;
        double theLeastLoadedServerValue = MAXIMUM_LOAD;
        for (int i = 0; i < servers.length; ++i) {
            if (servers[i].currentLoadPercentage < theLeastLoadedServerValue) {
                theLeastLoadedServerValue = servers[i].currentLoadPercentage;
                theLeastLoadedServerIndex = i;
            }
        }
        return servers[theLeastLoadedServerIndex];
    }
}
