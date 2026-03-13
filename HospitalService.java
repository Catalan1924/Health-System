public abstract class HospitalService {
    private String serviceName;

    public HospitalService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public abstract void executeService();
}